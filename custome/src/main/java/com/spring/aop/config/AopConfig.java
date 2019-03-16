package com.spring.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author dmz
 * @date Create in 21:37 2019/3/16
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
@ComponentScan("com.spring.aop.service")
public class AopConfig {
    //    @Pointcut("execution(* *.*.service.Test.*(..)) && within(com.spring.aop.*))")
//    @Pointcut("execution(* *.*.service.Test.*(..)) && @within(com.spring.aop.AopAnnotation))")
//    @Pointcut("execution(* *..service.Test.*(..))")
    @Pointcut("execution(* *..service.Test.test(int,*))&&bean(test)&& args(num,*)")
    public void pointCut(int num) {
    }

    @Before(value = "pointCut(num)")
    public void before(JoinPoint joinPoint,int num) {
        System.out.println(joinPoint.getArgs()[0]);
        System.out.println("参数为"+num);
        System.out.println("before执行了");
    }

    @After(value = "pointCut(num)")
    public void after(int num) {
        System.out.println("after执行了");
    }

    @AfterReturning(value = "pointCut(num)")
    public void afterReturning(int num) {
        System.out.println("afterReturning执行了");
    }

    @AfterThrowing(value = "pointCut(num)")
    public void afterThrowing(int num) {
        System.out.println("afterThrowing执行了");
    }

    @Around(value = "pointCut(num)")
    public void around(ProceedingJoinPoint joinPoint,int num) throws Throwable {
        System.out.println("around开始执行了");
        joinPoint.proceed();
        System.out.println("around结束执行了");
    }
}
