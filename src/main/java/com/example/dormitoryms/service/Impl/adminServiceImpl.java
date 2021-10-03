package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.adminMapper;
import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private adminMapper adminmapper;

    @Override
    public Admin identifyAdminByPhone(Admin admin) {
        return adminmapper.identifyAdminByPhone(admin);
    }

    @Override
    public Admin identifyAdminByEmail(Admin admin) {
        return adminmapper.identifyAdminByEmail(admin);
    }
}
