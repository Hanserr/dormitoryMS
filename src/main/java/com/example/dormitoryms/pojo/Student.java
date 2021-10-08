package com.example.dormitoryms.pojo;

import lombok.*;
import java.io.Serializable;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id ;
    private String stuid;
    private String name;
    private Integer age;
    private Integer gender;
    private Integer grade;
    private Integer department;
    //departmentName用于配合联合查询
    private String departmentName;
    private Integer role;
    private Integer dormitoryNum;
    private String status;
}


