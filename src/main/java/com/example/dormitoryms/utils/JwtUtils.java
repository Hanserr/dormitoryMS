package com.example.dormitoryms.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * jwt工具类
 * @Auther Shelter
 * @Date 10/3/2021
 **/
@Data
@Component
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtUtils {

    private long expire;
    private String key;
    private String header;

    //生成jwt
    public String generateToken(String username){
        Date date = new Date();
        Date expDate = new Date(date.getTime()+1000*expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(expDate)//过期时间
                .signWith(SignatureAlgorithm.HS512,key)
                .compact();
    }

    //解析jwt
    public Claims getClaimsToken(String jwt){
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    //判断过期
    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}
