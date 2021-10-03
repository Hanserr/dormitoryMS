package com.example.dormitoryms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface aliossService {
    //创建bucket
    void createBucket();
    //上传文件
    String upload(MultipartFile file);
    //下载文件
    void download(String fileName) throws IOException;
    //列举文件
    void listFile();
}
