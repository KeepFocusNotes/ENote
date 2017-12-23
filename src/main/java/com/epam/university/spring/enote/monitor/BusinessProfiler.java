package com.epam.university.spring.enote.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class BusinessProfiler {
    Logger log = LoggerFactory.getLogger(BusinessProfiler.class);

    @Pointcut("execution(* com.epam.university.spring.enote.services.*.*(..))")

    //@Pointcut("execution(public * *(..))")
    public void businessMethods() {
    }

    @Around("businessMethods()")
    public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        log.info("Going to call the method.");
        Object output = proceedingJoinPoint.proceed();
        log.info("Method execution completed.");
        long elapsedTime = System.nanoTime() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return output;
    }
}
