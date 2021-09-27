//package com.example.dormitoryms.utils;
//import com.example.dormitoryms.pojo.Admin;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
///**
// * @Auther Shelter
// * @Date 8/10/2021
// **/
//public class TokenConvertAndParse {
//    public String getKey(String username,String password,String expireTime){
//        String jointString = "{username:"+username+",password:"+password+",createTime:+"+new +",expireTime+"+expireTime+"}";
//        try {
//            MessageDigest messageDigest= MessageDigest.getInstance("MD5");
//            messageDigest.digest(jointString);
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        return
//    }
//    void refreshToken(String token){}
//    void logOut(String token){}
//    Admin getAdminInfo(String token){}
//}
