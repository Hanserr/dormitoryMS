package com.example.dormitoryms.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *自定义适配器
 * @Auther shelter
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
            "/getCaptcha",
    };

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable()
            //登陆配置
            .formLogin()
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
                //jwt过滤器
                .addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                //添加自定义json过滤器至默认过滤器处
                .addFilterAt(authenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    //自定义json认证过滤器
    myAuthenticationFilter authenticationFilter() throws Exception{
        myAuthenticationFilter filter = new myAuthenticationFilter();
        filter.setAuthenticationManager(super.authenticationManagerBean());
        //认证成功和失败处理器
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setFilterProcessesUrl("/login");

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
