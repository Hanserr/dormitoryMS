package com.example.dormitoryms.pojo;

import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    private String avatarUrl;
    private Integer gender;
    private Integer grade;
    private Integer department;
    private Integer role;
    private Integer dormitoryNum;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String password;
    private String phone;
    private String departmentName;
}


