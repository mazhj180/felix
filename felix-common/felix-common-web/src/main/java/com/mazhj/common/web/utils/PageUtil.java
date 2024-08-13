package com.mazhj.common.web.utils;

import jakarta.servlet.http.HttpServletRequest;

public class PageUtil {

    public static int getPageNum(){
        HttpServletRequest request = ServletUtil.getRequest();
        int pageNum = request.getIntHeader("pageNum");
        return pageNum == -1?1:pageNum;
    }

    public static int getPageSize(){
        HttpServletRequest request = ServletUtil.getRequest();
        int pageSize = request.getIntHeader("pageSize");
        return pageSize == -1?1:pageSize;
    }

    public static int getFromNum() {
        return (getPageNum() - 1) * getPageSize();
    }
}
