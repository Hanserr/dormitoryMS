package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.Student;

import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
public interface stuService {
    void insert(Student s);
    List<Student> queryByCondition(String s);
}
