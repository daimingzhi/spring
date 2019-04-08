package com.study.spring.springdatajpa.config;

import com.study.spring.springdatajpa.repository.impl.CustomRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author dmz
 * @date Create in 22:41 2019/4/8
 * @desc
 */
//自定义CustomRepositoryFactoryBean,继承JpaRepositoryFactoryBean
public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    //重写createRepositoryFactory方法，用当前的CustomRepositoryFactory创建实例
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomRepositoryFactory(entityManager);  //该类见下面的定义
    }

    //定义一个私有的静态类，并继承JpaRepositoryFactory
    private static class CustomRepositoryFactory extends JpaRepositoryFactory {

        //构造函数
        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new CustomRepositoryImpl<>(information.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 获得当前自定义类的类型
            return CustomRepositoryImpl.class;
        }
    }
}
