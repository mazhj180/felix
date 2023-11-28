package com.mazhj.felix.homepage.service.impl;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.pojo.dto.BookshelfDTO;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.feign.search.clients.SearchClient;
import com.mazhj.felix.feign.user.clients.UserClient;
import com.mazhj.felix.homepage.pojo.param.Reason;
import com.mazhj.felix.homepage.service.GuessYouService;
import com.mazhj.felix.quartz.anno.Invoke;
import com.mazhj.felix.quartz.anno.QuartzTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mazhj
 */
@Service
public class GuessYouServiceImpl implements GuessYouService {

    private final GuessYouTask guessYouTask;

    public GuessYouServiceImpl(
            GuessYouTask guessYouTask
            ) {
        this.guessYouTask = guessYouTask;
    }

    @Override
    public List<BookDTO> getPossibleLikes(String userId, Reason reason) {
        String reasonKey = KeyBuilder.Homepage.getReasonKey();
        String likeKey = KeyBuilder.Homepage.getPossibleLikeKey();
        guessYouTask.redisService.setHashVal(reasonKey,userId,reason);
        List<BookDTO> bookDTOS = guessYouTask.redisService.getHashVal(likeKey,userId);
        if (bookDTOS.isEmpty()){
            guessYouTask.guessYou();
            bookDTOS = guessYouTask.redisService.getHashVal(likeKey,userId);
        }
        return bookDTOS;
    }

    @QuartzTask
    public static class GuessYouTask{

        private final RedisService redisService;

        private final UserClient userClient;

        private final BookClient bookClient;

        private final SearchClient searchClient;
        public GuessYouTask(
                RedisService redisService,
                UserClient userClient,
                BookClient bookClient,
                SearchClient searchClient
                ) {
            this.redisService = redisService;
            this.userClient = userClient;
            this.bookClient = bookClient;
            this.searchClient = searchClient;
        }

        @Invoke
        public void guessYou(){
            String reasonKey = KeyBuilder.Homepage.getReasonKey();
            String listKey = KeyBuilder.Homepage.getPossibleLikeKey();
            Map<String, Reason> reasonMap = this.redisService.getHashEntries(reasonKey);
            List<BookDTO> books = this.bookClient.getBookList();

            for (Map.Entry<String, Reason> entry : reasonMap.entrySet()) {
                //不同用户下，每本书获得的分数。
                HashMap<String, Integer> scoreMap = new HashMap<>();
                //获取用户书架中图书信息。以此为根据打分
                String userId = entry.getKey();
                List<BookshelfDTO> bookshelf = this.userClient.getBookshelf(userId);

                Map<Integer, Long> collect = bookshelf.stream()
                        .collect(Collectors
                                .groupingBy(BookshelfDTO::getCategory, Collectors.counting()));

                for (Map.Entry<Integer, Long> c : collect.entrySet()) {
                    books.forEach(book -> {
                        if (book.getCategory().equals(c.getKey())){
                            scoreMap.put(book.getBookId(), c.getValue().intValue());
                        }else {
                            scoreMap.put(book.getBookId(),0);
                        }
                    });
                }
                //获取用户提供的理由，以此为根据打分
                Reason reason = entry.getValue();
                List<String> sr = reason.getSearchRecord();
                if (!sr.isEmpty()) {
                    sr.forEach(record -> {
                        List<BookDTO> bsn = this.searchClient.searchBooksByName(record);
                        List<BookDTO> bsk = this.searchClient.searchBooksByKeyword(record);

                        for (BookDTO dto : Stream.concat(bsn.stream(),bsk.stream()).toList()) {
                            Integer score = scoreMap.get(dto.getBookId());
                            scoreMap.put(dto.getBookId(),score + 1);
                        }
                    });
                }
                List<String> lr = reason.getLookRecent();
                if (!lr.isEmpty()) {
                    lr.forEach(look -> {
                        BookDTO book = this.bookClient.getBookByBookId(look);
                        for (BookDTO dto : books) {
                            if (dto.getCategory().equals(book.getCategory())){
                                Integer score = scoreMap.get(dto.getBookId());
                                scoreMap.put(dto.getBookId(),score + 1);
                            }
                        }
                    });
                }

                //根据每本书评分获取前10
                int rank = 10;
                List<Map.Entry<String, Integer>> entryList = new ArrayList<>(scoreMap.entrySet()
                        .stream().toList());
                for (int i = 0; i < rank; i++) {
                    int maxIndex = i;
                    Map.Entry<String, Integer> maxEntry = entryList.get(i);
                    Map.Entry<String,Integer> tempEntry;
                    for (int j = i + 1; j < entryList.size() - 1; j++) {
                        if (entryList.get(j).getValue() > maxEntry.getValue()){
                            maxEntry = entryList.get(j);
                            maxIndex = j;
                        }
                    }
                    if (maxIndex != i){
                        tempEntry = entryList.get(maxIndex);
                        entryList.set(maxIndex,entryList.get(i));
                        entryList.set(i,tempEntry);
                    }
                }

                List<String> bookIds = new ArrayList<>();
                for (int i = 0; i < rank; i++) {
                    bookIds.add(entryList.get(i).getKey());
                }
                this.redisService.setHashVal(listKey,userId,bookIds);
            }
        }
    }
}
