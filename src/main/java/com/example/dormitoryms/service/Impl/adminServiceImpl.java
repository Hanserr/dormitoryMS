package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.adminMapper;
import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

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

//    @Override
//    public Admin identifyAdminByEmail(Admin admin) {
//        return adminmapper.identifyAdminByEmail(admin);
//    }

    @Override
    public Admin getOneByUsername(String username) {
////      //邮箱正则
//        String regEmail = "/^([a-zA-Z0-9-._])+\\@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$/";
//        //手机号正则验证
//        String regPhone = "/^1[0-9]{10}$/";
//
//        Pattern pEmail = Pattern.compile(regEmail);
//        Pattern pPhone = Pattern.compile(regPhone);
//
//        if (pEmail.matcher(username).find()){
//            Admin a = new Admin();
//            a.setUsername(username);
//            Admin i = adminmapper.queryOne(a);
//            if (i==null){
//                throw new UsernameNotFoundException("用户名或密码不正确");
//            }
//            return i;
//        }else if (pPhone.matcher(username).find()){
//            Admin a = new Admin();
//            a.setUsername(username);
//            Admin i = adminmapper.queryOne(a);
//            if (i==null){
//                throw new UsernameNotFoundException("用户名或密码不正确");
//            }
//            return i;
//        }else{
//            throw new UsernameNotFoundException("用户名或密码不正确");
//        }
//    }

        Admin admin = adminmapper.queryOneByAccount(username);
        if (admin != null){
            return admin;
        }
        throw new UsernameNotFoundException("用户名或密码不正确");
    }
}
