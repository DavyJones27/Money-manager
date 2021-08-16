package com.davy.trans;

import com.davy.trans.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {

        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        AuthFilter authFilter = new AuthFilter();

        registrationBean.setFilter(authFilter);

        registrationBean.addUrlPatterns("/categories/*");

        return registrationBean;

    }

}
