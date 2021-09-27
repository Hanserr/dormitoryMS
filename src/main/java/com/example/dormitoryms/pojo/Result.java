package com.example.dormitoryms.pojo;

import lombok.*;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private List<T> result;
    private List<T> departmentList;
    private List<T> dormitoryList;
    private T t;
    private Integer num;

    public Result(int code, String msg, List<T> result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    public Result(int code, String msg, List<T> result, List departmentList, List dormitoryList) {
        this.code = code;
        this.msg = msg;
        this.result = result;
        this.departmentList = departmentList;
        this.dormitoryList = dormitoryList;
    }
    public Result(int code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.t = t;
    }

    /**
     * @param code 状态码
     * @param msg 信息
     * @param num 宿舍容量
     * @param result 结果集
     */
    public Result(int code,String msg,Integer num,List<T> result){
        this.code = code;
        this.msg = msg;
        this.num = num;
        this.result = result;
    };
}
