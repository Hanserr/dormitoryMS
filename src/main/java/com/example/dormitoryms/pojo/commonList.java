package com.example.dormitoryms.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Auther Shelter
 * @Date 7/18/2021
 *用于返回院系,宿舍号列表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class commonList implements Serializable {
    private static final long serialVersionUID = 1L;
    private String text;
    private String value;
}
