package com.mengya.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableConfigurationProperties(value = AliyunOssProperties.class)
public class AliyunOssConfig {

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    /**
     * 生成一个名称为ossClient的OSS对象并交给Spring容器管理
     * 当然你也可以为其指定变量名称
     */
    @Bean
    public OSS ossClient(){
        OSS ossClient = new OSSClientBuilder().build(aliyunOssProperties.getEndPoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret());
        try {
            if (ossClient.doesBucketExist(aliyunOssProperties.getBucketName())) {
                System.out.println("您已经创建Bucket：" + aliyunOssProperties.getBucketName() + "。");
            } else {
                System.out.println("您的Bucket不存在，创建Bucket：" + aliyunOssProperties.getBucketName() + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(aliyunOssProperties.getBucketName());
            }
        }catch (Exception e){
            log.error("对象存储初始化异常",e);
        }
        return ossClient;
    }


}
