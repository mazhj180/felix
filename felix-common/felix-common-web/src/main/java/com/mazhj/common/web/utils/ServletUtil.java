package com.mazhj.common.web.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author mazhj
 */
public final class ServletUtil {

    public static HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
