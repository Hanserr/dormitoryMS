package com.example.dormitoryms.security;

import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Auther Shelter
 * @Date 10/4/2021
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    adminService adminservice;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("test2");
        //判断username是否为空
        if (username == null || "".equals(username)){
            throw new RuntimeException("用户不能为空");
        }
        //根据username查数据库，空则抛异常
        Admin a = adminservice.getOneByUsername(username);
        if(a == null){
            throw new RuntimeException("用户名或密码错误");
        }
        //返回用户
        return new AccountUser(a.getUid(),a.getPhone(),a.getUsername(),a.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_normal"));
    }

    //权限
//    public List<GrantedAuthority> getUserAuthority(Long userId){
//
//    }
}
