package com.example.dormitoryms.web;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.example.dormitoryms.pojo.Const;
import com.example.dormitoryms.pojo.Result;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * @Auther Shelter
 * @Date 10/6/2021
 **/
@RestController
public class AuthController extends BaseController {
    @Autowired
    Producer producer;

    @GetMapping("/getCaptcha")
    public Result captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        //创建验证码图片
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"png",outputStream);
        //base64头部标准
        String str = "data:image/png;base64,";
        //编码
        String base64Img = str+Base64.getEncoder().encodeToString(outputStream.toByteArray());

        //存入redis
        redisUtil.hset(Const.CAPTCHA_KEY,key,code,120);
        return Result.success(MapUtil.builder().put("token",key).put("captchaImg",base64Img).build());
    }
}
