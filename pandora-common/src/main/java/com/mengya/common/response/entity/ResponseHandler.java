package com.mengya.common.response.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengya.common.exception.entity.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    private ThreadLocal<ObjectMapper>  mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annotations = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class,
            ExceptionHandler.class
    };


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement element = methodParameter.getAnnotatedElement();
        return Arrays.stream(annotations).anyMatch(annotation -> annotation.isAnnotation() && element.isAnnotationPresent(annotation));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Object out;
        ObjectMapper mapper = mapperThreadLocal.get();

        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(body instanceof ApiResult){
            out = body;
        }
        else if (body instanceof ErrorCode){
            ErrorCode errorCode = (ErrorCode) body;
            out = new ApiResult(errorCode.getCode(),errorCode.getMsg(),"");
        }
        else if (body instanceof String){
            ApiResult result = new ApiResult(200,"",body);
            try {
                out = mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                out = new ApiResult(ErrorCode.JSON_PARSE_ERROR.getCode(),e.getMessage(),"");
            }
        }
        else{
            out = new ApiResult(200,"",body);
        }
        return out;
    }
}
