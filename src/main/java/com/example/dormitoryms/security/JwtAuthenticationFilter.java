package com.example.dormitoryms.security;

import cn.hutool.core.util.StrUtil;
import com.example.dormitoryms.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther Shelter
 * @Date 10/3/2021
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    String jwt = request.getHeader(jwtUtils.getHeader());
        System.out.println(jwt);
    if (StrUtil.isBlankIfStr(jwt)){
        chain.doFilter(request,response);
        return;
    }

    Claims claims = jwtUtils.getClaimsToken(jwt);
    if (claims == null){
        throw new JwtException("token exception");
    }
    if (jwtUtils.isTokenExpired(claims)){
        throw new JwtException("token has expired");

    }
        String username = claims.getSubject();
        //获取用户权限信息
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null,null);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request,response);

    }
}
