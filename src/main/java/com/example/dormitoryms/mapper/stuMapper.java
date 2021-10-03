package com.example.dormitoryms.mapper;

import com.example.dormitoryms.pojo.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@Mapper
public interface stuMapper {
    //插入数据
    @Insert("insert student values (null,#{stuid},#{name},#{age},#{gender},null,#{department},null,#{role},#{dormitoryNum},null)")
    void insertStudent(Student student);
    //模糊查询学生
    @Select("SELECT s.stuid,s.`name`,s.age,s.gender,f.name departmentName,s.role,s.dormitoryNum FROM student s LEFT JOIN faculty f on s.department = f.id WHERE CONCAT(IFNULL(s.name,''),IFNULL(f.name,''),IFNULL(s.role,''),IFNULL(s.dormitoryNum,'')) LIKE '%${value}%'")
    List<Student> queryByCondition(String s);
    //修改学生信息
    @Update("update student set name = #{name},role = #{role},dormitoryNum = #{dormitoryNum} where stuid = #{stuid}")
    void updateStu(Student student);
    //查询单条数据
    @Select("SELECT s.stuid,s.`name`,s.age,s.gender,f.name departmentName,s.role,s.dormitoryNum FROM student s LEFT JOIN faculty f on s.department = f.id where s.stuid = #{stuid}")
    Student queryOne(String s);
    //查询指定宿舍当前入住人数
    @Select("select count(dl.capacity) from student s left JOIN dormitorymiddlelist dml on dml.number = s.dormitoryNum LEFT JOIN dormitorylist dl on dl.type = dml.type where s.dormitoryNum = #{dormitoryNum}")
    Integer queryMenber(Student student);
    //删除指定学生
    @Delete("delete from student where stuid = ${value}")
    void deleteStu(String stuid);
    //查询指定宿舍内是否已有寝室长
    @Select("SELECT count( stu.role) from student stu where stu.dormitoryNum = ${value} and stu.role=1")
    Integer queryLeaderNum(Integer dorNum);
}
