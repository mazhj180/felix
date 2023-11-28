package com.mazhj.felix.homepage.service.impl;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.feign.user.clients.UserClient;
import com.mazhj.felix.homepage.pojo.model.Book;
import com.mazhj.felix.homepage.pojo.param.Reason;
import com.mazhj.felix.homepage.service.GuessYouService;
import com.mazhj.felix.quartz.anno.Invoke;
import com.mazhj.felix.quartz.anno.QuartzTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Book> getPossibleLikes(String userId, Reason reason) {
        String key = KeyBuilder.Homepage.getReasonKey();
        Reason r = guessYouTask.redisService.getHashVal(key,userId);
        if (r == null){
            guessYouTask.redisService.setHashVal(key,userId,reason);
            r = reason;
        }

        return null;
    }

    @QuartzTask
    public static class GuessYouTask{

        private final RedisService redisService;

        private final UserClient userClient;

        private final BookClient bookClient;
        public GuessYouTask(
                RedisService redisService,
                UserClient userClient,
                BookClient bookClient
                ) {
            this.redisService = redisService;
            this.userClient = userClient;
            this.bookClient = bookClient;
        }

        @Invoke
        public void guessYou(){
            String reasonKey = KeyBuilder.Homepage.getReasonKey();
            String listKey = KeyBuilder.Homepage.getPossibleLikeKey();
            Map<String, Reason> reasonMap = this.redisService.getHashEntries(reasonKey);
            Map<String, List<BookDTO>> likeMap = this.redisService.getHashEntries(listKey);
            List<BookDTO> bookDto = this.bookClient.getBookList();

        }
    }
}
