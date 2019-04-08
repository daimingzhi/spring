package com.study.spring.springweb.mapper;

import com.study.spring.springweb.SpringWebApplication;
import com.study.spring.springweb.domain.Coffee;
import com.study.spring.springweb.domain.CoffeeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author dmz
 * @date Create in 16:58 2019/4/8
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class)
public class CoffeeMapperTest {

    @Autowired
    CoffeeMapper coffeeMapper;

    @Test
    public void test() {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andIdEqualTo(1L);
        List<Coffee> coffees = coffeeMapper.selectByExample(example);
        System.out.println(coffees);
    }
}