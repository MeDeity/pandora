package com.mengya.generator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(MyBatisGeneratorProperties.class)
@PropertySource(value="classpath:mybatis-generate.yml",encoding = "utf-8",factory = YamlPropertyResourceFactory.class)
public class MyBatisGenerateConfig {

    MyBatisGeneratorProperties myBatisGeneratorProperties;

    public MyBatisGenerateConfig(MyBatisGeneratorProperties myBatisGeneratorProperties){
        this.myBatisGeneratorProperties = myBatisGeneratorProperties;
    }
}
