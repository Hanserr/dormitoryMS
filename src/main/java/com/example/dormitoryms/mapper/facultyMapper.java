package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.Faculty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface facultyMapper {
    //查询所有院系部门
    @Select("select * from faculty")
    List<Faculty> getDepartmentList();
}
