package com.example.dormitoryms.web;

import com.alibaba.fastjson.JSONObject;
import com.example.dormitoryms.pojo.*;
import com.example.dormitoryms.service.Impl.dormitoryServiceImpl;
import com.example.dormitoryms.service.Impl.stuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@RestController
public class stuController {
    @Autowired
    private stuServiceImpl stuService;

    @Autowired
    private dormitoryServiceImpl dormitoryService;

    /**
     *添加单条学生信息
     * @param student 传入的学生数据
     * @return 返回结果
     */
    @PreAuthorize("hasAuthority('sys:manageStu:commonAdd')")
    @PostMapping("/insert")
    public Result insert(@RequestBody Student student){
        try {
            student.setDepartment(student.getDepartment()+1);
            stuService.insert(student);
            return Result.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("添加失败");
        }
    }

    /**
     * 模糊查询学生数据
     * @param s 用户输入的关键字
     * @return 返回查询结果列表
     */
    @PreAuthorize("hasAuthority('sys:queryStu')")
    @PostMapping("/queryByCondition")
    public Result query(@RequestBody String s){
        List<Student> l;
        List<commonList> departmentLists = new ArrayList<>();
        List<commonList> dormitoryLists = new ArrayList<>();
        try {
            //学生列表
            l = stuService.queryByCondition(s);
            //判断是否查询到数据
            if (l==null){
                return Result.fail("查询失败");
            }
            //公共列表
            List<String>departmentCommonList = new ArrayList<>();
            List<Integer>dormitoryCommonList = new ArrayList<>();
            //循环去重并存入院系公共列表
            for (int i = 0;i<l.size();i++){
                if (departmentCommonList.contains(l.get(i).getDepartmentName())){
                    continue;
                }
                departmentCommonList.add(l.get(i).getDepartmentName());
            }
            //将去重完的结果存入院系列表
            for (int i = 0;i<departmentCommonList.size();i++){
                departmentLists.add(new commonList(departmentCommonList.get(i),departmentCommonList.get(i)));
            }
            //循环去重并存入宿舍号公共列表
            for (int i = 0;i<l.size();i++){
                if (dormitoryCommonList.contains(l.get(i).getDormitoryNum())){
                    continue;
                }
                dormitoryCommonList.add(l.get(i).getDormitoryNum());
            }
            //将去重完的结果存入宿舍号列表
            for (int i = 0;i<dormitoryCommonList.size();i++) {
                dormitoryLists.add(new commonList(dormitoryCommonList.get(i).toString(), dormitoryCommonList.get(i).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
        List r = new ArrayList();
        r.add(l);
        r.add(departmentLists);
        r.add(dormitoryLists);
        return Result.success(r);
    }

    /**
     * 查询单条学生数据
     * @param s 学号
     * @return 返回单条数据
     */
    @PreAuthorize("hasAuthority('sys:queryOneStu')")
    @PostMapping("queryOne")
    public Result queryOne(@RequestBody String s){
        try {
            Student student = stuService.queryOne(s);
            if (student != null){
                return Result.success(student);
            }
            return Result.fail("查询失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("数据为空");
        }
    }

    /**
     * 修改学生数据
     * @param student 学生数据
     * @return 返回修改完的结果用以更新列表
     */
    @PreAuthorize("hasAuthority('sys:updateStu')")
    @PostMapping("/updateStu")
    public Result updateStu(@RequestBody Student student){
        try {
            if (stuService.queryLeaderNum(student.getDormitoryNum())>0 && student.getRole()==1){
                return Result.fail("当前宿舍已有寝室长");
            }
            stuService.updateStu(student);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("修改失败");
        }
        return Result.success(stuService.queryOne(student.getStuid()));
    }

    /**
     * 批量添加学生
     * @param students json字符串数据
     * @return 返回结果
     */
    @PreAuthorize("hasAuthority('sys:addStuList')")
    @PostMapping("/addStudent")
    public Result addStudent(@RequestBody String students){
        List<Student> list = JSONObject.parseArray(students,Student.class);
        //添加前检查宿舍容量
        for (Student s:list){
            if (dormitoryService.queryCapacity(s).equals(stuService.queryMember(s))){
                return Result.fail("["+s.getDormitoryNum()+"]宿舍已满");
            }
        }
        //检查通过后可添加
        for (Student student:list){
            student.setDepartment(student.getDepartment()+1);
            try {
                stuService.insert(student);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail("插入数据失败");
            }
        }
        return Result.success("插入数据成功");
    }

    /**
     * 删除指定学生
     * @param stuid 传入学号
     * @return 返回删除结果
     */
    @PreAuthorize("hasAuthority('sys:deleteStu')")
    @PostMapping("/delete")
    public Result deleteStu(@RequestBody String stuid){
        try {
            stuService.deleteStu(stuid);
            return Result.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("删除失败");
        }
    }
}
