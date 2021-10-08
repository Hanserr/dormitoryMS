package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface adminMapper {
    //通过手机号和密码判断管理员账号
    @Select("select username,phone,email from admin where phone = #{phone} and password = #{password}")
    Admin identifyAdminByPhone(Admin admin);
    //通过邮箱和密码判断管理员账号
    @Select("select username,phone,email from admin where email = #{email} and password = #{password}")
    Admin identifyAdminByEmail(Admin admin);
    //
    @Select("select * from admin where email = '${value}' or phone = '${value}'")
    Admin queryOneByAccount(String account);
}
