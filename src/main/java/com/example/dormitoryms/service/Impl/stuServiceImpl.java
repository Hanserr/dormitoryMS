package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.stuMapper;
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
    public List<Faculty> getDepartmentList() {
        return stumapper.getDepartmentList();
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
    public Integer queryCapacity(Student student) {
        return stumapper.queryCapacity(student);
    }

    @Override
    public List<Student> queryStuByDorNum(Integer dorNum) {
        return stumapper.queryStuByDorNum(dorNum);
    }

    @Override
    public void deleteStu(Integer stuid) {
        stumapper.deleteStu(stuid);
    }

    @Override
    public List<Integer> getDorListByLimit(Integer initial, Integer size) {
        return stumapper.getDorListByLimit(initial,size);
    }

    @Override
    public List<DormitoryDetail> getDormitoryDetailByDNum(Integer id) {
        return stumapper.getDormitoryDetailByDNum(id);
    }


}
