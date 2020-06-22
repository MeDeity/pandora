package com.mengya.swagger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Component
//@EnableSwagger2
public class SwaggerConfig {

    //@Autowired 根据类型注入
    //@Resource  根据名称注入
    @Autowired
    private ApiInfo apiInfo;


    @Bean(value = "restApi")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("接口文档")
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.liyisoft.masterpat.resource"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     *构建api文档的详细信息函数
     */
    @Bean
    private ApiInfo updateApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 使用 Swagger2 构建RESTful API")
                .contact(new Contact("MeDeity", "https://github.com/MeDeity", "langrenbule@gmail.com"))
                .version("1.0.0")
                .description("API 描述")
                .build();
    }

}
