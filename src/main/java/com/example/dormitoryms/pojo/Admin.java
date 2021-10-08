package com.example.dormitoryms.pojo;

import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther Shelter
 * @Date 8/11/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<String> Authorities;
}
