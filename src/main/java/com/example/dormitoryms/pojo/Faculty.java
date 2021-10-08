package com.example.dormitoryms.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Auther Shelter
 * @Date 7/17/2021
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
}
