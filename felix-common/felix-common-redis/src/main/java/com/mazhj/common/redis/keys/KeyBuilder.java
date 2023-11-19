package com.mazhj.common.redis.keys;

import com.mazhj.common.core.enums.ServiceModel;

public final class KeyBuilder {

    public static class User{
        private static final String prefix = ServiceModel.USER.str();
    }

    public static class Book{
        private static final String prefix = ServiceModel.BOOK.str();

        public static String getBookDetailKey(String bookId){
            return String.format(prefix + "detail:key-%s",bookId);
        }

        public static String getChaptersKey(String bookId){
            return String.format(prefix + "chapters:key-%s",bookId);
        }

        public static String getChapterKey(String booId){
            return String.format(prefix + "chapter:key-%s",booId);
        }

        public static String getChapterNodeKey(String bookId){
            return String.format(prefix + "chapter-node:key-%s",bookId);
        }
    }

    public static class Homepage{
        private static final String prefix = ServiceModel.HOMEPAGE.str();

        public static final String getRanksKey(){
            return prefix + "rank:key";
        }

        public static final String getPossibleLikeKey(){
            return prefix + "possible-like:key";
        }
    }

}
