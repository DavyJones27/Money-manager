package com.davy.trans;

import com.davy.trans.aop.LoggingInterceptor;
import com.davy.trans.filters.GetUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GetUser());
        registry.addInterceptor(new LoggingInterceptor());
    }
}
