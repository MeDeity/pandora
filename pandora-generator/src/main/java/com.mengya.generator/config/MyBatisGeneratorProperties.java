package com.mengya.generator.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "generate")
public class MyBatisGeneratorProperties {
    private String databaseUrl;
    private String databaseUser;
    private String databasePassword;
}