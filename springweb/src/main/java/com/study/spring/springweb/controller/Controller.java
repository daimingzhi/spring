package com.study.spring.springweb.controller;

import com.study.spring.springweb.domain.Coffee;
import com.study.spring.springweb.domain.CoffeeExample;
import com.study.spring.springweb.mapper.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author dmz
 * @date Create in 17:08 2019/3/29
 */
@RestController
@RequestMapping("/demo")
@Validated
public class Controller {

    @Autowired
    private CoffeeMapper coffeeMapper;

    @RequestMapping
    public String controller(@Valid @Positive(message = "商品售价格式无效") BigDecimal installmentAmount) {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andIdEqualTo(1L);
        List<Coffee> coffees = coffeeMapper.selectByExample(example);
        return coffees.toString();
    }
}
