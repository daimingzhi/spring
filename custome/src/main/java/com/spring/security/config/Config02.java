package com.spring.security.config;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author dmz
 * @date Create in 21:53 2019/3/20
 */
public class Config02 extends AbstractGenericHttpMessageConverter {
    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
}
