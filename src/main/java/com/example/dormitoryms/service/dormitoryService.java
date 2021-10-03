package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Student;
import java.util.List;

public interface dormitoryService {
    List<Student> queryStuByDorNum(Integer dorNum);
    Integer queryCapacity(Student student);
    List<Integer>getDorListByLimit(Integer initial,Integer size);
    List<DormitoryDetail>getDormitoryDetailByDNum(Integer id);
}
