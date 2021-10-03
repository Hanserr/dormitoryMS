package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.facultyMapper;
import com.example.dormitoryms.pojo.Faculty;
import com.example.dormitoryms.service.facultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@Service
public class facultyServiceImpl implements facultyService {
    @Autowired
    private facultyMapper facultyMapper;

    @Override
    public List<Faculty> getDepartmentList() {
        return facultyMapper.getDepartmentList();
    }
}
