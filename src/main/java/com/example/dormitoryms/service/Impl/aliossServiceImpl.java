package com.example.dormitoryms.service.Impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.example.dormitoryms.pojo.AliOss;
import com.example.dormitoryms.service.aliossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther Shelter
 * @Date 10/2/2021
 **/
public class aliossServiceImpl implements aliossService {
    @Autowired
    private AliOss oss;

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    //创建bucket
    @Override
    public void createBucket() {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
            }else
                throw new RuntimeException(bucketName+"已存在");
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

    }

    @Override
    public String upload(MultipartFile file) {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            ossClient.putObject(bucketName, file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        return null;
    }

    @Override
    public void download(String fileName) throws IOException {

    }

    @Override
    public void listFile() {

    }
}
