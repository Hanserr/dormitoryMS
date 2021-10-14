package com.example.dormitoryms.web;

import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.pojo.Result;
import com.example.dormitoryms.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@RestController
public class adminController {
    @Autowired
    private adminService adminService;

}
