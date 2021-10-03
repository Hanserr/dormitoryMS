package com.example.dormitoryms.web;

import com.alibaba.fastjson.JSONObject;
import com.example.dormitoryms.pojo.*;
import com.example.dormitoryms.service.Impl.dormitoryServiceImpl;
import com.example.dormitoryms.service.Impl.stuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import java.util.*;


/**
 * @Auther Shelter
 * @Date 7/8/2021
 **/
@RestController
public class stuController {
//    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
//        System.out.println("连接成功");
//        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
//    }
    @Autowired
    private stuServiceImpl stuService;

    @Autowired
    private dormitoryServiceImpl dormitoryService;

    /**
     *添加单条学生信息
     * @param student 传入的学生数据
     * @return 返回结果
     */
    @PostMapping("/insert")
    public Result<Student> insert(@RequestBody Student student){
        try {
            student.setDepartment(student.getDepartment()+1);
            stuService.insert(student);
            return new Result<>(200,"添加成功",stuService.queryOne(student.getStuid()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"添加失败");
        }
    }

    /**
     * 模糊查询学生数据
     * @param s 用户输入的关键字
     * @return 返回查询结果列表
     */
    @PostMapping("/queryByCondition")
    public Result<Student> query(@RequestBody String s){
        List<Student> l;
        List<commonList> departmentLists = new ArrayList<>();
        List<commonList> dormitoryLists = new ArrayList<>();
        try {
            //学生列表
            l = stuService.queryByCondition(s);
            //判断是否查询到数据
            if (l==null){
                return new Result<>(500,"数据为空");
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
            return new Result<>(500,"查询失败");
        }
        return new Result<>(200, "查询成功", l,departmentLists,dormitoryLists);
    }

    /**
     * 查询单条学生数据
     * @param s 学号
     * @return 返回单条数据
     */
    @PostMapping("queryOne")
    public Result<Student>queryOne(@RequestBody String s){
        try {
            return new Result<>(200,"数据为空",stuService.queryOne(s));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"数据为空");
        }
    }

    /**
     * 修改学生数据
     * @param student 学生数据
     * @return 返回修改完的结果用以更新列表
     */
    @PostMapping("/updateStu")
    public Result<Student> updateStu(@RequestBody Student student){
        try {
            if (stuService.queryLeaderNum(student.getDormitoryNum())>0 && student.getRole()==1){
                return new Result<>(500,"当前宿舍已有寝室长");
            }
            stuService.updateStu(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"修改失败");
        }
        return new Result<>(200,"修改成功",stuService.queryOne(student.getStuid()));
    }

    /**
     * 添加学生
     * @param students json字符串数据
     * @return 返回结果
     */
    @PostMapping("/addStudent")
    public Result<Student> addStudent(@RequestBody String students){
        List<Student> list = JSONObject.parseArray(students,Student.class);
        //添加前检查宿舍容量
        for (Student s:list){
            if (dormitoryService.queryCapacity(s).equals(stuService.queryMember(s))){
                return new Result<>(500,"["+s.getDormitoryNum()+"]宿舍已满");
            }
        }
        //检查通过后可添加
        for (Student student:list){
            student.setDepartment(student.getDepartment()+1);
            try {
                stuService.insert(student);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result<>(500,"插入数据失败");
            }
        }
        return new Result<>(200,"插入数据成功");
    }

    /**
     * 删除指定学生
     * @param stuid 传入学号
     * @return 返回删除结果
     */
    @PostMapping("/delete")
    public Result<Student> deleteStu(@RequestBody String stuid){
        try {
            stuService.deleteStu(stuid);
            return new Result<>(200,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500,"删除失败");
        }
    }
}
