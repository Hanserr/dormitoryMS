package com.example.dormitoryms.web;

import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Result;
import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.Impl.dormitoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@RestController
public class dormitoryController {
    @Autowired
    private dormitoryServiceImpl dormitoryService;

    /**
     * 根据寝室号查询所有学生
     * @param num 寝室号
     * @return 返回结果
     */
    @PreAuthorize("hasAuthority('sys:queryStuByDorNum')")
    @PostMapping("/queryStuByDorNum")
    public Result queryStuByDorNum(@RequestBody Integer num){
        try {
            return Result.success(dormitoryService.queryStuByDorNum(num));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
    }

    /**
     * 查询寝室容量
     * @param num 寝室号
     * @return 返回容量和宿舍当前居住者信息
     */
    @PreAuthorize("hasAuthority('sys:queryCapacity')")
    @PostMapping("/queryCapacity")
    public Result queryCapacity(@RequestBody Integer num){
        try {
            Student s = new Student();
            s.setDormitoryNum(num);
            List l = new ArrayList();
            l.add(dormitoryService.queryCapacity(s));
            l.add(dormitoryService.queryStuByDorNum(num));
            return dormitoryService.queryCapacity(s)==null?Result.fail("不存在此宿舍"):Result.success(l);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
    }

    /**
     * 懒加载每次返回10条宿舍数据(貌似没用)
     * @param initialNumber be used to record has returned data size,this param reserved by client
     * @return D-Num data
     */
    @PreAuthorize("hasAuthority('sys:getDorListByLimit')")
    @GetMapping("/getDorListByLimit")
    public Result getDorListByLimit(Integer initialNumber){
        try {
            return Result.success(dormitoryService.getDorListByLimit(initialNumber,initialNumber+10));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
    }

    /**
     * 返回宿舍内部详细信息
     * @param id D-Num
     * @return dormitory detail
     */
    @PreAuthorize("hasAuthority('sys:getDorDetail')")
    @GetMapping("/getDormitoryDetail")
    public Result getdetial(Integer id){
        try {
            if(dormitoryService.getDormitoryDetailByDNum(id).size()==0){
                return Result.fail("查询失败");
            }
            return Result.success(dormitoryService.getDormitoryDetailByDNum(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
    }
}
