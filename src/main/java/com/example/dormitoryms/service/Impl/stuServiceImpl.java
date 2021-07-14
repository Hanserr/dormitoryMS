package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.stuMapper;
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
    public void insert(Student s) {
        stumapper.insertStudent(s);
    }

    @Override
    public List<Student> queryByCondition(String s) {
        return stumapper.queryByCondition(s);
    }
}
