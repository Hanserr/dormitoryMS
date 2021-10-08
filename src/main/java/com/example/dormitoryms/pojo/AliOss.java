package com.example.dormitoryms.pojo;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
@ConfigurationProperties(prefix = "alioss")
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AliOss {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
