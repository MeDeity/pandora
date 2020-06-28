package com.mengya.generator.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "generate")
public class MyBatisGeneratorProperties {
    /**数据库连接地址*/
    private String databaseUrl;
    /**数据库用户名*/
    private String databaseUser;
    /**数据库密码*/
    private String databasePassword;
    /**作者*/
    private String author;
    /**驱动类名*/
    private String driverName;
    /**基础包名*/
    private String packageName;
    /**基类*/
    private String superBaseEntity;
    /**生成文件的物理路径*/
    private String saveLocation;
}