package com.study.spring.springdatajpa.repository;

import com.study.spring.springdatajpa.entity.User;

import java.util.List;

/**
 * @author dmz
 * @date Create in 9:22 2019/4/2
 */
public interface UserRepository extends CustomRepository<User, Integer> {
    /**
     * 根据id查询
     *
     * @param part
     * @return
     */
    List<User> findFirstByUseEmailLike(String part);
}
