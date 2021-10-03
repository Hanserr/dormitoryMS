package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.Student;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
public interface stuService {
    void insert(Student student);
    List<Student> queryByCondition(String s);
    void updateStu(Student student);
    Student queryOne(String s);
    Integer queryMember(Student student);
    void deleteStu(String stuid);
    Integer queryLeaderNum(Integer dorNum);
}
