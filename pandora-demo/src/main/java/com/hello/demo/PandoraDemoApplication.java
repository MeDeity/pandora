package com.hello.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mengya.*","com.hello.demo"})
public class PandoraDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandoraDemoApplication.class, args);
    }

}
