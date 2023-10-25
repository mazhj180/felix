package com.mazhj.common.web.request;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author mazhj
 */
@Slf4j
public class Params extends HashMap<String,Object> {

    /**
     * 获取整型参数值
     * @param paramName
     * @return
     */
    public Integer getIntValue(String paramName){
        Integer value = 0;
        Object object = this.get(paramName);

        if (null == object) {
            return value;
        }

        try{
            value = Integer.parseInt(this.get(paramName).toString());
        } catch (Exception ex){
            value = 0;
            log.error("获取参数{}转换整型异常！{}", paramName, ex);
        }
        return value;
    }

    /**
     * 获取字符串参数值
     * @param paramName
     * @return
     */
    public String getStringValue(String paramName){
        String value = "";
        Object object = this.get(paramName);

        if (null == object) {
            return value;
        }

        return object.toString();
    }

}
