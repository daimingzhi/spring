package com.study.spring.httpmessageconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author dmz
 * @date Create in 14:15 2019/3/29
 */
public class JacksonService {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    }
}
