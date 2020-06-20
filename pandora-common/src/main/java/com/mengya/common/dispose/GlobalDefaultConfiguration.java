package com.mengya.common.dispose;

import com.mengya.common.dispose.exception.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfiguration {

    @Bean
    public GlobalExceptionHandler globalDefaultExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public GlobalResponseHandler commonResponseDataAdvice(GlobalDefaultProperties globalDefaultProperties) {
        return new GlobalResponseHandler(globalDefaultProperties);
    }

}
