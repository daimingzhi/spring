package com.study.spring.springdatajpa.entity;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.util.Collection;
import java.util.Collections;

/**
 * @author dmz
 * @date Create in 22:59 2019/4/8
 * @desc
 */
class AnAggregateRoot {

    @DomainEvents
    Collection<User> domainEvents() {
        // … return events you want to get published here
        return Collections.EMPTY_LIST;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        System.out.println("时间执行完成");
        // … potentially clean up domain events list
    }
}