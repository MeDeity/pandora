package com.hello.demo.controller;

import com.mengya.generator.MyBatisGenerator;
import com.mengya.generator.config.MyBatisGeneratorProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "示例模块")

public class HelloController {

    @Autowired
    private MyBatisGenerator myBatisGenerator;

    @RequestMapping("/")
    @ApiOperation(value = "欢迎入口",notes = "示例swagger接口的使用")
    public String index(){
        return "hello world";
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        myBatisGenerator.startGenerator();
        return "";
    }

}
