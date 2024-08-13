package com.mazhj.common.web.controller;

import com.alibaba.fastjson2.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.common.web.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author mazhj
 */
public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtils.parseDate(text,"yyyy-MM-dd"));
            }
        });
    }

    protected final void startPage(){
        HttpServletRequest request = ServletUtil.getRequest();
        int pageNum = request.getIntHeader("pageNum");
        int pageSize = request.getIntHeader("pageSize");
        if (pageSize == -1 || pageNum == -1) {
            pageSize = 1;
            pageNum = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
    }

    protected final AjaxResult success(){
        return AjaxResult.Builder.success();
    }

    protected final AjaxResult success(Object data){
        return AjaxResult.Builder.success(data);
    }


}
