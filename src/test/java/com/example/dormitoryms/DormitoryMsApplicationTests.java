package com.example.dormitoryms;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DormitoryMsApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        boolean s = bCryptPasswordEncoder.matches("$2a$10$jeVF5FGxKHPGFmDU0kRnXe8a/tmm1id8w7bkgSz7Dd9JBe9jBdMMy","Admin123~");
        String aa = "Admin123~";
//        String t = bCryptPasswordEncoder.encode(aa);
//        System.out.println(t);
        System.out.println(bCryptPasswordEncoder.matches("Admin123~","$2a$10$sQ8H2gAWQqOu4fV1his8F.XJSD15CGj4kddCxJU7Zlzmbni9NktGS"));
    }
}
