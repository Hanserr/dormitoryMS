package com.example.dormitoryms.web;

import com.example.dormitoryms.pojo.DormitoryDetail;
import com.example.dormitoryms.pojo.Result;
import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.Impl.dormitoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
    @PostMapping("/queryStuByDorNum")
    public Result<Student> queryStuByDorNum(@RequestBody Integer num){
        try {
            return new Result<>(200,"查询成功",dormitoryService.queryStuByDorNum(num));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"查询失败");
        }
    }

    /**
     * 查询寝室容量
     * @param num 寝室号
     * @return 返回容量和宿舍当前居住者信息
     */
    @PostMapping("/queryCapacity")
    public Result<Student> queryCapacity(@RequestBody Integer num){
        try {
            Student s = new Student();
            s.setDormitoryNum(num);
            return dormitoryService.queryCapacity(s)==null?new Result<>(500,"不存在此宿舍"):new Result<>(200,"查询成功",dormitoryService.queryCapacity(s),dormitoryService.queryStuByDorNum(num));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"查询失败");
        }
    }

    /**
     * 懒加载每次返回10条宿舍数据(貌似没用)
     * @param initialNumber be used to record has returned data size,this param reserved by client
     * @return D-Num data
     */
    @GetMapping("/getDorListByLimit")
    public Result<Integer> getDorListByLimit(Integer initialNumber){
        try {
            return new Result<>(200,"查询成功",dormitoryService.getDorListByLimit(initialNumber,initialNumber+10));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"查询失败",new ArrayList<>());
        }
    }

    /**
     * 返回宿舍内部详细信息
     * @param id D-Num
     * @return dormitory detail
     */
    @GetMapping("/getDormitoryDetail")
    public Result<DormitoryDetail>getdetial(Integer id){
        try {
            if(dormitoryService.getDormitoryDetailByDNum(id).size()==0){
                return new Result<>(500,"查询失败");
            }
            return new Result<>(200,"查询成功",dormitoryService.getDormitoryDetailByDNum(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"查询失败");
        }
    }
}
