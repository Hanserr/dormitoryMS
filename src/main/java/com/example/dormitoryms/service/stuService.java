package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Faculty;
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
    List<Faculty> getDepartmentList();
    Student queryOne(String s);
    Integer queryMember(Student student);
    Integer queryCapacity(Student student);
    List<Student> queryStuByDorNum(Integer dorNum);
    void deleteStu(Integer stuid);
    List<Integer>getDorListByLimit(Integer initial,Integer size);
    List<DormitoryDetail>getDormitoryDetailByDNum(Integer id);
}
