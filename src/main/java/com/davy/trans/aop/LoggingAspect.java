package com.davy.trans.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Configuration
@Aspect
public class LoggingAspect {

    public static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Around("applicationControllerBean() && applicationControllerPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.debug(
                "Request for {}.{}() with arguments[s]={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );

        Instant start = Instant.now();

        Object returnValue = joinPoint.proceed();

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();

        logger.debug(
                "Response for {}.{} with result= {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                returnValue
        );

        logger.info("Time taken  😊 🤣 " + timeElapsed);

        return returnValue;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void applicationControllerPackage() {

    }

    @Pointcut("within(com.davy.trans.resources.*)")
    public void applicationControllerBean() {

    }

    @AfterThrowing(pointcut = "applicationExceptionPackage()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{} with cause = {}, with message = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "null",
                e.getMessage() != null ? e.getMessage() : "null");

    }

    @Pointcut("within(com.davy.trans..*)")
    public void applicationExceptionPackage() {

    }
}
