package com.example.dormitoryms.service;

import com.example.dormitoryms.pojo.Admin;

public interface adminService {
    Admin getOneByUsername(String username);
    String getUserAuthorityInfo(String username);
    void clearAuthorityInfo(String username);
}
