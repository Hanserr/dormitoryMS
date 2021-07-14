package com.example.dormitoryms.pojo;

import lombok.*;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Result<T>{
    private int code;
    private String msg;
    private List<T> result;
}
