package com.mengya.generator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(MyBatisGeneratorProperties.class)
@PropertySource("classpath:mybatis-generate.properties")
public class MyBatisGenerateConfig {

    public MyBatisGenerateConfig(MyBatisGeneratorProperties myBatisGeneratorProperties){
    }
}
