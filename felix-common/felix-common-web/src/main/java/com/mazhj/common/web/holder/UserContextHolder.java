package com.mazhj.common.web.holder;

public class UserContextHolder {

    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    public static void set(String user) {
        userHolder.set(user);
    }

    public static String get() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }

}
