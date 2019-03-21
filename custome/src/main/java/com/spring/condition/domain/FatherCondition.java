package com.spring.condition.domain;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:40 2019/3/22
 */
public class FatherCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String who = context.getEnvironment().getProperty("who");
        return true;
    }
}
