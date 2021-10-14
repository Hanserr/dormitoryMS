package com.example.dormitoryms.service.Impl;

import com.example.dormitoryms.mapper.adminMapper;
import com.example.dormitoryms.pojo.Admin;
import com.example.dormitoryms.service.adminService;
import com.example.dormitoryms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private adminMapper adminmapper;

    @Autowired
    RedisUtil redisUtil;

    //获取权限信息
    @Override
    public String getUserAuthorityInfo(String username) {
        //缓存中有权限信息直接返回
        if (redisUtil.hasKey("GrantedAuthority"+username)){
            return (String) redisUtil.get("GrantedAuthority"+username);
        }

        List<String> authority = adminmapper.authorityList(username);
        StringBuffer sb = new StringBuffer();
        //循环添加字符串
        for (String s:authority){
            sb.append(s).append(",");
        }
        //删除字符串最后一位逗号
        String str = sb.deleteCharAt(sb.length() - 1).toString();
        //redis缓存权限
        redisUtil.set("GrantedAuthority"+username,str,60*60);
        return str;
    }

    @Override
    public void clearAuthorityInfo(String username) {
        redisUtil.del("GrantedAuthority"+username);
    }

    @Override
    public Admin getOneByUsername(String username) {
        return adminmapper.queryOneByAccount(username);
    }
}
