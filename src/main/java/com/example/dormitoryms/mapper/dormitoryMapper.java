package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@Mapper
public interface dormitoryMapper {
    //查询指定宿舍容量
    @Select("select DISTINCT(dl.capacity) from student s left JOIN dormitorymiddlelist dml on dml.number = s.dormitoryNum LEFT JOIN dormitorylist dl on dl.type = dml.type where s.dormitoryNum = #{dormitoryNum}")
    Integer queryCapacity(Student student);
    //根据寝室号查询学生
    @Select("SELECT s.stuid,s.`name`,s.age,s.gender,f.name departmentName,s.role,s.dormitoryNum FROM student s LEFT JOIN faculty f on s.department = f.id where s.dormitoryNum = ${value}")
    List<Student> queryStuByDorNum(Integer dorNum);
    //懒加载宿舍，每次十条数据
    @Select("select number from dormitorymiddlelist LIMIT #{initial},#{size}")
    List<Integer> getDorListByLimit(@Param("initial") Integer initial, @Param("size") Integer size);
    //通过宿舍号查寝室
    @Select("select * from dormitorydetail where id = ${value}")
    List<DormitoryDetail>getDormitoryDetailByDNum(Integer id);
}
