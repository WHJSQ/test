package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by Administrator on 2018/9/5.
 */
@Configuration
public class FileUploadConfig {
    @Bean
    public MultipartResolver custom(){
        return new CommonsMultipartResolver();
    }
}
