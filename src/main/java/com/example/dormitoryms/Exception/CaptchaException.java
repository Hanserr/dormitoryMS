package com.example.dormitoryms.Exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Auther Shelter
 * @Date 10/7/2021
 **/
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg){
        super(msg);
    }
}
