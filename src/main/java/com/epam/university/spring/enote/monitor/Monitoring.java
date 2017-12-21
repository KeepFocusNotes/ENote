package com.epam.university.spring.enote.monitor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Aspect
@Component
@PropertySource("classpath:aspect.properties")
public class Monitoring {

  private static boolean enabled;

  @Around(value = "@annotation(Monitor)")
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

    if (enabled) {
      long start = System.currentTimeMillis();
      Object proceed = null;
      for(int i = 0;i < 10_000;i++) {
        proceed = joinPoint.proceed();
      }
      long time = System.currentTimeMillis() - start;
      System.out.println("------PERFORMANCE MONITOR------");
      System.out.println(joinPoint.getSignature().toShortString() + " executed in " + time + " ms");
      System.out.println("------PERFORMANCE MONITOR------");
      System.out.println(proceed);
      return proceed;
    }
    return joinPoint.proceed();
  }

  @Value("${performance.monitor}")
  public void setEnabled(boolean value) {
    enabled = value;
  }
}
