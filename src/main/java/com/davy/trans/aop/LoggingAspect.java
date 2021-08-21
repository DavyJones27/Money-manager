package com.davy.trans.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Aspect
public class LoggingAspect {

    public static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
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

        logger.info("Time taken " + new SimpleDateFormat("mm:ss:SSS")
                .format(new Date(timeElapsed)));

        return returnValue;
    }
}
