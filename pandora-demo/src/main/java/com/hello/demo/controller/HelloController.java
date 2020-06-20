package com.hello.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "示例模块")
public class HelloController {

    @RequestMapping("/")
    @ApiOperation(value = "欢迎入口",notes = "示例swagger接口的使用")
    public String index(){
        return "hello world";
    }

}
