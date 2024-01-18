package com.mazhj.felix.homepage.service.impl;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.pojo.dto.BookshelfDTO;
import com.mazhj.common.pojo.enums.BookCategory;
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

import java.security.Key;
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

    @Override
    public void collectReason(Reason reason) {
        String reasonKey = KeyBuilder.Homepage.getReasonKey();

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
            String likeKey = KeyBuilder.Homepage.getPossibleLikeKey();
            Map<String, Reason> reasonMap = this.redisService.getHashEntries(reasonKey);
            List<BookDTO> books = this.bookClient.getBookList();

            for (Map.Entry<String, Reason> entry : reasonMap.entrySet()) {
                String userId = entry.getKey();
                List<BookshelfDTO> bookshelf = this.userClient.getBookshelf(userId);
                //构建评委链
                Judge bookshelfJudge = new BookshelfJudge(bookshelf);
                Judge reasonJudge = new ReasonJudge(searchClient, bookClient);
                bookshelfJudge.addJudge(reasonJudge);

                //评委们开始打分
                bookshelfJudge.scoring(entry,books);

                //根据每本书评分获取前10
                List<String> bookIds = ranking(reasonJudge.scoreMap, 10);
                this.redisService.setHashVal(likeKey,userId,bookIds);
            }
        }


        private List<String> ranking(Map<String, Integer> scoreMap,int rank){
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
            return bookIds;
        }
    }

    private static abstract class Judge{

        protected Judge judge;

        protected Map<String,Integer> scoreMap = new HashMap<>();


        public void addJudge(Judge judge){
            this.judge = judge;
        }

        /**
         * 评分逻辑
         * @param books 全部图书
         * @param reasonEntry 用户理由
         */
        public abstract void scoring(Map.Entry<String, Reason> reasonEntry,List<BookDTO> books);

    }

    private static class BookshelfJudge extends Judge {

        private final List<BookshelfDTO> bookshelf;

        public BookshelfJudge(List<BookshelfDTO> bookshelf) {
            this.bookshelf = bookshelf;
        }

        private Map<BookCategory,List<BookshelfDTO>> generateCategoryBuckets(List<BookshelfDTO> bookshelf){
            Map<BookCategory,List<BookshelfDTO>> categoryMap = new HashMap<>();
            for (BookCategory category : BookCategory.values()) {
                categoryMap.put(category,new ArrayList<>());
            }

            for (BookshelfDTO dto : bookshelf) {
                for (BookCategory category : dto.getCategories()) {
                    categoryMap.get(category).add(dto);
                }
            }
            return categoryMap;
        }

        @Override
        public void scoring(Map.Entry<String, Reason> reasonEntry, List<BookDTO> books) {
            Map<String, Long> collect = bookshelf.stream()
                    .collect(Collectors
                            .groupingBy(BookshelfDTO::getAuthorName, Collectors.counting()));

            for (Map.Entry<String, Long> c : collect.entrySet()) {
                books.forEach(book -> {
                    if (book.getAuthorName().equals(c.getKey())){
                        super.scoreMap.put(book.getBookId(), c.getValue().intValue());
                    }else {
                        super.scoreMap.put(book.getBookId(),0);
                    }
                });
            }
            Map<BookCategory,List<BookshelfDTO>> categoryBuckets = this.generateCategoryBuckets(bookshelf);
            categoryBuckets.forEach((k,v) -> {
                for (BookDTO book : books) {
                    if(book.getCategories().stream().anyMatch(bookCategory -> bookCategory.equals(k))){
                        Integer score = super.scoreMap.get(book.getBookId());
                        super.scoreMap.put(book.getBookId(),score);
                    }
                }
            });
            judge.scoring(reasonEntry,books);
        }
    }

    private static class ReasonJudge extends Judge {

        private final SearchClient searchClient;

        private final BookClient bookClient;

        public ReasonJudge(SearchClient searchClient,BookClient bookClient) {
            this.searchClient = searchClient;
            this.bookClient = bookClient;
        }

        @Override
        public void scoring(Map.Entry<String, Reason> reasonEntry, List<BookDTO> books) {
            Reason reason = reasonEntry.getValue();
            List<String> sr = reason.getSearchRecord();
            if (!sr.isEmpty()) {
                sr.forEach(record -> {
                    List<BookDTO> bsn = this.searchClient.searchBooksByName(record);
                    List<BookDTO> bsk = this.searchClient.searchBooksByKeyword(record);

                    for (BookDTO dto : Stream.concat(bsn.stream(),bsk.stream()).toList()) {
                        Integer score = super.scoreMap.get(dto.getBookId());
                        super.scoreMap.put(dto.getBookId(),score + 1);
                    }
                });
            }
            List<String> lr = reason.getLookRecent();
            if (!lr.isEmpty()) {
                lr.forEach(look -> {
                    BookDTO book = this.bookClient.getBookByBookId(look);
                    for (BookDTO dto : books) {
                        if (dto.getAuthorName().equals(book.getAuthorName())){
                            Integer score = super.scoreMap.get(dto.getBookId());
                            super.scoreMap.put(dto.getBookId(),score + 1);
                        }
                    }
                });
            }
        }
    }
}
