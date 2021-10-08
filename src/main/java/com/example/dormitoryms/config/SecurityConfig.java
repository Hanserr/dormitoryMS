package com.example.dormitoryms.config;

import com.alibaba.fastjson.JSON;
import com.example.dormitoryms.pojo.Result;
import com.example.dormitoryms.security.*;
import com.example.dormitoryms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther Shelter
 * @Date 10/3/2021
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    CaptchaFilter captchaFilter;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    CustomizeAuthenticationEntryPoint myauthenticationEntryPoint;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //白名单
    private static final String[] URL_WHITELIST = {
            "/adminLogin",
            "/getCaptcha",
    };

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable()
            //登陆配置
            .formLogin()
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
            //禁用session
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //配置拦截规则
            .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
            //异常处理
            .and().exceptionHandling()
                .authenticationEntryPoint(myauthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
            //配置自定义过滤器
            .and()
                //验证账号密码前检查验证码
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                //添加自定义json过滤器至默认过滤器处
                .addFilterAt(authenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                //jwt过滤器
                .addFilter(jwtAuthenticationFilter());
    }

    //自定义json认证过滤器
    @Bean
    myAuthenticationFilter authenticationFilter() throws Exception{
        myAuthenticationFilter filter = new myAuthenticationFilter();
        filter.setAuthenticationManager(super.authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                AccountUser accountUser = (AccountUser) authentication.getPrincipal();
                String jwtToken = jwtUtils.generateToken(accountUser.getUsername());
                httpServletResponse.getWriter().write(JSON.toJSONString(Result.success(jwtToken)));
            }
        });
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
