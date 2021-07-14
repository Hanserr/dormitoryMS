package com.example.dormitoryms.pojo;

import lombok.Data;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@Data
public class Student {
    private Integer id ;
    private String stuid;
    private String name;
    private Integer age;
    private Integer gender;
    private String department;
    private String role;
    private Integer dormitoryNum;
}
