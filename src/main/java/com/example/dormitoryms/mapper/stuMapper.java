package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@Mapper
public interface stuMapper {
//插入数据
    @Insert("insert student values (null,#stuid,#name,#age,#gender,#department,#role,#dormitoryNum)")
    void insertStudent(Student stu);
//模糊查询
    @Select("SELECT * FROM student WHERE CONCAT(IFNULL(name,''),IFNULL(department,''),IFNULL(role,'')) LIKE '%${value}%'")
    List<Student> queryByCondition(String s);
}
