package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.dormitoryMapper;
import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.dormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@Service
public class dormitoryServiceImpl implements dormitoryService {
    @Autowired
    private dormitoryMapper dormitoryMapper;

    @Override
    public Integer queryCapacity(Student student) {
        return dormitoryMapper.queryCapacity(student);
    }

    @Override
    public List<Student> queryStuByDorNum(Integer dorNum) {
        return dormitoryMapper.queryStuByDorNum(dorNum);
    }

    @Override
    public List<Integer> getDorListByLimit(Integer initial, Integer size) {
        return dormitoryMapper.getDorListByLimit(initial,size);
    }

    @Override
    public List<DormitoryDetail> getDormitoryDetailByDNum(Integer id) {
        return dormitoryMapper.getDormitoryDetailByDNum(id);
    }

}
