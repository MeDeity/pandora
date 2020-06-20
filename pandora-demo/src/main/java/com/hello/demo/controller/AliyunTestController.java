package com.hello.demo.controller;

import com.mengya.oss.service.impl.AliyunOssServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oss")
public class AliyunTestController {

    @Autowired
    private AliyunOssServiceImpl aliyunOssService;

    @RequestMapping("/upload")
    @ApiOperation(value = "上传(文本内容)", notes = "上传(文本内容)",httpMethod = "GET")
    public ResponseEntity upload(String objectName, String content){
        aliyunOssService.upload(objectName,content);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/download")
    @ApiOperation(value = "下载文件", notes = "下载文件",httpMethod = "GET")
    public ResponseEntity<?> download(String objectName){
        aliyunOssService.downloadFile(objectName,"D:/1.jpeg");
        return ResponseEntity.ok().build();
    }

}
