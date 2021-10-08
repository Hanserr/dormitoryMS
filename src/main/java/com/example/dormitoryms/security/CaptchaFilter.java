package com.example.dormitoryms.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dormitoryms.Exception.CaptchaException;
import com.example.dormitoryms.pojo.Const;
import com.example.dormitoryms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Auther Shelter
 * @Date 10/7/2021
 **/
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if ("/login".equals(url) && request.getMethod().equals("POST")){
            //获取字符串
            StringBuffer sb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //替换空格和换行符
            String jsonStr = sb.toString().replaceAll("\\s","").replaceAll("\n","");
            //转成json对象
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            //获取json对象
            String code = jsonObject.getString("code");
            String key= jsonObject.getString("token");

            //post请求只可获取一次值，此处保存用户名密码
            request.setAttribute("username",jsonObject.getString("username"));
            request.setAttribute("password",jsonObject.getString("password"));

            //校验验证码
            try {
                validate(request,code,key);
            } catch (AuthenticationException e) {
                //交给认证失败处理器处理
                loginFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request,response);
    }

    //校验验证码
    private void validate(HttpServletRequest request,String code,String key){
        if ((code == null || code.equals(""))||(key == null || key.equals(""))){
            throw new CaptchaException("验证码错误");
        }
        redisUtil.get(key);
        if (code.equals(redisUtil.hget(Const.CAPTCHA_KEY,key))){
            throw new CaptchaException("验证码错误");
        }
        //验证完删除
        redisUtil.hdel(Const.CAPTCHA_KEY,key);
    }
}
