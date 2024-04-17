package com.zym.dpan.utils;





import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * ClassName: R
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/7 16:44
 * @Version 1.0
 */
public class R extends HashMap<String,Object> {
    public static final long serialVersionUID = 1;

    public R(){
        put("status",0);
        put("message","success");
        put("data",new HashMap<>());
    }

    public static R error(int code,String msg){
        R r = new R();
        r.put("status",code);
        r.put("message",msg);
        return r;
    }

    public static R error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }

    public static R error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");
    }

    public static R ok(Map<String,Object> map){
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(String msg){
        R r = new R();
        r.put("message",msg);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R data(String key,Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        put("data",map);
        return this;
    }

    public R data(){
        Map<String,Object> map = new HashMap<>();
        put("data",map);
        return this;
    }
    @Override
    public R put(String key, Object value){
        super.put(key,value);
        return this;
    }

    public Integer getCode(){return (Integer) this.get("status");}





}
