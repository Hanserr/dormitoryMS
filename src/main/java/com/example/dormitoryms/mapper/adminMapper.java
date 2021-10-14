package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理员mapper
 */

@Mapper
public interface adminMapper {
    //通过用户名查询角色
    @Select("select * from admin where username = ${value}")
    Admin queryOneByAccount(String account);

    //
    @Select("select am.perms from admin a LEFT JOIN admin_middle_menu amm on amm.adminID = a.uid LEFT JOIN admin_menu am on amm.menuID = am.id where a.username = ${value}")
    List<String> authorityList(String username);
}
