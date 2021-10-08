package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.Admin;

public interface adminService {
    Admin identifyAdminByPhone(Admin admin);
//    Admin identifyAdminByEmail(Admin admin);
    Admin getOneByUsername(String username);
}
