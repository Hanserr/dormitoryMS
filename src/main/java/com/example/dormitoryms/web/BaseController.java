package com.example.dormitoryms.web;

import com.example.dormitoryms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther Shelter
 * @Date 10/6/2021
 **/
public class BaseController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisUtil redisUtil;
}
