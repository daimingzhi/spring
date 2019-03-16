package com.spring.aop.config;

import com.spring.aop.service.MyInterface;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author dmz
 * @date Create in 22:55 2019/3/16
 */
@Configuration
@Aspect
@ComponentScan("com.spring.aop.service")
@EnableAspectJAutoProxy
public class EnhanceAop {
    @DeclareParents(value = "(com.spring.aop.service.Test+)", defaultImpl = MyInterfaceImpl.class)
    public static MyInterface myInterface;
}
