package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.stuMapper;
import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Faculty;
import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.stuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@Service
public class stuServiceImpl implements stuService {
    @Autowired
    private stuMapper stumapper;

    @Override
    public Student queryByStuid(String stuid) {
        return stumapper.queryByStuid(stuid);
    }

    @Override
    public void insert(Student s) {
        stumapper.insertStudent(s);
    }

    @Override
    public List<Student> queryByCondition(String s) {
        return stumapper.queryByCondition(s);
    }

    @Override
    public void updateStu(Student student) {
        stumapper.updateStu(student);
    }

    @Override
    public Student queryOne(String s) {
        return stumapper.queryOne(s);
    }

    @Override
    public Integer queryMember(Student student) {
        return stumapper.queryMenber(student);
    }

    @Override
    public void deleteStu(String stuid) {
        stumapper.deleteStu(stuid);
    }

    @Override
    public Integer queryLeaderNum(Integer dorNum) {
        return stumapper.queryLeaderNum(dorNum);
    }

}
