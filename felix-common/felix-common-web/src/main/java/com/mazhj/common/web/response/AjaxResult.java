package com.mazhj.common.web.response;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.List;

/**
 * @author mazhj
 */
public class AjaxResult extends HashMap<String,Object> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String DATA_TAG = "data";

    public static final String MSG_TAG = "message";

    public static final String CODE_TAG = "code";

    public static class Builder {

        public static AjaxResult success(){
            return new AjaxResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        }

        public static AjaxResult success(Object data){
            return new AjaxResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),data);
        }

        public static AjaxResult unAuthorized(){
            return new AjaxResult(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }

        public static AjaxResult badRequest(){
            return new AjaxResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
        }

        public static AjaxResult error(){
            return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }


    private AjaxResult() {}

    private AjaxResult(int code, String msg) {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    private AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if (null != data){
            super.put(DATA_TAG,data);
        }
    }

    public AjaxResult buildMsg(String msg){
        super.put(MSG_TAG,msg);
        return this;
    }

    public String getDataValForStr(String key){
        Object o = this.get(AjaxResult.DATA_TAG);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(o));
        return jsonObject.getString(key);
    }

    public JSONObject getData(){
        Object o = this.get(AjaxResult.DATA_TAG);
        return JSON.parseObject(JSON.toJSONString(o));
    }

    public <T> T deserialize(Class<T> clazz){
        Object o = this.get(AjaxResult.DATA_TAG);
        return JSON.parseObject(JSON.toJSONString(o),clazz);
    }

    public <T> List<T> deserializeL(){
        Object o = this.get(AjaxResult.DATA_TAG);
        return JSON.parseObject(JSON.toJSONString(o), new TypeReference<>() {});
    }



}
