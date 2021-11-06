package com.example.dormitoryms.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.adminService;
import com.example.dormitoryms.service.stuService;
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

    @Autowired
    stuService  stuservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String jsonStr = username.toString().replaceAll("\\s","").replaceAll("\n","");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String mark = jsonObject.getString("mark");
        String nameStr = jsonObject.getString("username");
        //判断username是否为空
        if (nameStr == null || "".equals(username)){
            throw new RuntimeException("用户不能为空");
        }
        //根据标识判断用户类型
        if ("admin".equals(mark)){
            Admin a = adminservice.getOneByUsername(nameStr);
            if (a != null){
                return new AccountUser(String.valueOf(a.getUid()),a.getPhone(),a.getUsername(),a.getPassword(), getUserAuthority(a.getUsername()));
            }
        }else if ("user".equals(mark)){
            Student s = stuservice.queryByStuid(username);
            if (s != null){
                return new AccountUser(String.valueOf(s.getStuid()),s.getPhone(),s.getName(),s.getPassword(),getUserAuthority(s.getName()));
            }
        }
        return new AccountUser(null,null,null,null,getUserAuthority(null));
    }

    //权限
    public List<GrantedAuthority> getUserAuthority(String username){
        return AuthorityUtils.commaSeparatedStringToAuthorityList(adminservice.getUserAuthorityInfo(username));
    }
}
