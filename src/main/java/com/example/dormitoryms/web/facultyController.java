package com.example.dormitoryms.web;

import com.example.dormitoryms.pojo.Faculty;
import com.example.dormitoryms.pojo.Result;
import com.example.dormitoryms.service.Impl.facultyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@RestController
public class facultyController {
    @Autowired
    private facultyServiceImpl stuService;

    /**
     * 院系列表
     * @return 返回所有院系列表
     */
    @PreAuthorize("hasAuthority('sys:getDepList')")
    @GetMapping("/getDepartmentList")
    public Result getDepartmentList(){
        try {
            return Result.success(stuService.getDepartmentList());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
    }
}
