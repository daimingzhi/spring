package com.spring.condition.domain;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:39 2019/3/22
 */
public class MotherCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false; //context.getEnvironment().getProperty("who").equals("mother");
    }
}
