package com.example.dormitoryms.security;

import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 根据用户名返回用户对象
 * @Auther Shelter
 * @Date 10/4/2021
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    adminService adminservice;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
        return new AccountUser(a.getUid(),a.getPhone(),a.getUsername(),a.getPassword(), getUserAuthority(a.getUsername()));
    }

    //权限
    public List<GrantedAuthority> getUserAuthority(String username){
        return AuthorityUtils.commaSeparatedStringToAuthorityList(adminservice.getUserAuthorityInfo(username));
    }
}
