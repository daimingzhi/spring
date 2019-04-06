package com.study.spring.springweb.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author dmz
 * @date Create in 17:08 2019/3/29
 */
@RestController
@RequestMapping
@Validated
public class Controller {
    @RequestMapping
    public String Controller(@Valid @Positive(message = "商品售价格式无效") BigDecimal installmentAmount) {
        return "name";
    }
}
