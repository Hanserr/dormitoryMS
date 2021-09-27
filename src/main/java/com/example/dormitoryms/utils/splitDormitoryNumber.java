package com.example.dormitoryms.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割宿舍号
 * @Auther Shelter
 * @Date 7/31/2021
 **/
public class splitDormitoryNumber {
    /**
     *
     * @param num 传入宿舍号
     * @return 返回楼号——层数——编号
     */
    public List<Integer>split(int num){
        List<Integer>result = new ArrayList<>();
        result.add(num/1000);
        result.add(num/100%10);
        result.add(num%100);
        return result;
    }
}
