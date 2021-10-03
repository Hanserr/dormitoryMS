package com.example.dormitoryms.pojo;

import lombok.*;
/**
 * @Auther Shelter
 * @Date 8/11/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Admin {
    private Integer uid;
    private String username;
    private String password;
    private String phone;
    private String email;
}
