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

    /**
     * 管理员手机号登陆
     * @param admin 管理员信息
     * @return 是否登录成功
     */
    @PostMapping("/identifyAdminByPhone")
    public Result<Admin> identifyAdminByPhone(@RequestBody Admin admin){
        try {
            Admin a = adminService.identifyAdminByPhone(admin);
            if (a == null){
                return new Result<>(500,"账号或密码错误");
            }
            return new Result<>(200,"登陆成功",a);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"登陆失败");
        }
    }

    /**
     * 管理员邮箱登录
     * @param admin 管理员信息
     * @return 是否登陆成功
     */
    @PostMapping("/identifyAdminByEmail")
    public Result<Admin> identifyAdminByEmail(@RequestBody Admin admin){
        try {
            Admin a = adminService.identifyAdminByEmail(admin);
            if (a == null){
                return new Result<>(500,"账号或密码错误");
            }
            return new Result<>(200,"登陆成功",a);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"登陆失败");
        }
    }
}
