package com.study.spring.springdatajpa.repository.impl;

import com.study.spring.springdatajpa.repository.CustomRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author dmz
 * @date Create in 22:27 2019/4/8
 * @desc
 */
//要实现 CustomRepository接口，继承SimpleJpaRepository类让我们可以使用其提供的方法（如findAll）
public class CustomRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    //数据操作方法会用到entityManager,我们这里没有用到
    private final EntityManager entityManager;

    //CustomRepositoryImpl的构造函数，
    // 需当前处理的领域类和entityManager作为构造参数，在这里也给entityManager赋值了
    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void doWithId(ID id) {
        //定义数据访问操作，如调用findAll方法并构造一些查询条件
        System.out.println("拿到id干点事儿");
    }

}