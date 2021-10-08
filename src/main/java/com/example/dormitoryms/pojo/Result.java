package com.example.dormitoryms.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Object data;

    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result success(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result success(Object data){
        return success(200,"success",data);
    }
}
