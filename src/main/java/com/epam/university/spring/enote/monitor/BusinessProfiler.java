package com.epam.university.spring.enote.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BusinessProfiler {

  @Pointcut("execution(* com.epam.university.spring.enote.services.*.*(..))")
  //@Pointcut("execution(public * *(..))")
  public void businessMethods() {
  }

  @Around("businessMethods()")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    System.out.println("Going to call the method.");
    Object output = pjp.proceed();
    System.out.println("Method execution completed.");
    long elapsedTime = System.nanoTime() - start;
    System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
    return output;
  }
}
