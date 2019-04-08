package com.study.spring.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author dmz
 * @date Create in 22:26 2019/4/8
 * @desc
 */
@NoRepositoryBean  //指明当前这个接口不是领域类的接口
//我们自定义的CustomRepository继承了JpaRepository接口，具备JPA的能力
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    //要定义的数据操作方法在接口中定义
    void doWithId(ID id);
}

