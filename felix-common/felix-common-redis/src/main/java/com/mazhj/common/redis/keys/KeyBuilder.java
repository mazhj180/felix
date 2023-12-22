package com.mazhj.common.redis.keys;

import com.mazhj.common.core.enums.ServiceModel;

/**
 * @author mazhj
 */
public final class KeyBuilder {

    public static class User{
        private static final String PREFIX = ServiceModel.USER.str();
    }

    public static class Book{
        private static final String PREFIX = ServiceModel.BOOK.str();

        public static String getBookDetailKey(String bookId){
            return String.format(PREFIX + "detail:key-%s",bookId);
        }

        public static String getChaptersKey(String bookId){
            return String.format(PREFIX + "chapters:key-%s",bookId);
        }

        public static String getChapterKey(String booId){
            return String.format(PREFIX + "chapter:key-%s",booId);
        }

        public static String getChapterNodeKey(String bookId){
            return String.format(PREFIX + "chapter-node:key-%s",bookId);
        }

        public static String getBookCategoriesKey(String bookId){
            return String.format(PREFIX + "book-categories:key-%s",bookId);
        }
    }

    public static class Homepage{
        private static final String PREFIX = ServiceModel.HOMEPAGE.str();

        public static String getHotRankingsKey(){
            return PREFIX + "hot-rankings:key";
        }

        public static String getLikeRankingsKey(){
            return PREFIX + "like-rankings:key";
        }

        public static String getPossibleLikeKey(){
            return PREFIX + "possible-like:key";
        }

        public static String getReasonKey(){
            return PREFIX + "guess-you-reason:key";
        }
    }

    public static class Forum{
        private static final String PREFIX = ServiceModel.FORUM.str();

        public static String getTopicRemarkKey(String topicId){
            return String.format(PREFIX + "topic-remark:key-%s",topicId);
        }
    }

}
