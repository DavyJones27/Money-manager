package com.davy.trans.filters;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@Aspect
public class GetUser implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        System.out.println("ajay");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            CustomFilter filter = handlerMethod.getMethod().getAnnotation(CustomFilter.class);
            if (filter != null) {
                System.out.println(Arrays.toString(filter.auths()));
                System.out.println(request.getAttribute("userId"));
            }
        }
        return true;
    }
}