package com.example.dormitoryms.web;

import com.example.dormitoryms.pojo.Student;
import com.example.dormitoryms.service.Impl.stuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dormitoryms.pojo.Result;

import java.util.List;


/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@RestController
public class stuController {
    @Autowired
    private stuServiceImpl stuService;

    @PostMapping("/insert")
    public Result<Student> insert(@RequestBody Student student){
        try {
            stuService.insert(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"添加失败",null);
        }
        return new Result<>(200,"添加成功",null);
    }

    @PostMapping("/queryByCondition")
    public Result<Student> query(@RequestBody String s){
        List<Student> l;
        try {
            l = stuService.queryByCondition(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"查询失败",null);
        }
        return l==null?new Result<>(500,"数据为空",null):new Result<>(200, "查询成功", l);
    }
}
