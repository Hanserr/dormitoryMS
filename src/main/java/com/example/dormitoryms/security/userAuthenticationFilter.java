package com.example.dormitoryms.security;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther shelter
 * @Date 11/5/2021
 **/
public class userAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            //获取用户名密码
            String username = (String) request.getAttribute("username");
            String password = (String) request.getAttribute("password");
            if (username == null){
                username = "";
            }
            if (password == null){
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
            setDetails(request,authenticationToken);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }
        return super.attemptAuthentication(request,response);
    }
}
