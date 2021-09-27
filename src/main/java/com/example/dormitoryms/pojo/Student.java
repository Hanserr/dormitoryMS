package com.example.dormitoryms.pojo;

import lombok.*;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Student {
    private Integer id ;
    private String stuid;
    private String name;
    private Integer age;
    private String gender;
    private Integer grade;
    private Integer department;
    //departmentName用于配合联合查询
    private String departmentName;
    private String role;
    private Integer dormitoryNum;
    private String status;
}


