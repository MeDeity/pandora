#### 如何使用
1.引入相关的依赖
```pom
<dependency>
    <groupId>com.mengya</groupId>
    <artifactId>pandora-swagger</artifactId>
    <version>1.0.0</version>
</dependency>
```

2.swagger配置
```java
package com.hello.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("测试用接口组")
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.hello.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("MeDeity", "http://www.baidu.com/", "langrenbule@gmail.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger-bootstrap-ui-1.8.9开源接口ui
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
```

3.在接口上进行标注
```java
@RestController
@Api(tags = "示例模块")
public class HelloController {

    @RequestMapping("/")
    @ApiOperation(value = "欢迎入口",notes = "示例swagger接口的使用")
    public String index(){
        return "hello world";
    }

}
```

更多详情请参考demo中的使用示例
