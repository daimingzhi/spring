package com.spring.security.config;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author dmz
 * @date Create in 22:40 2019/3/20
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encode = MD5Encoder.encode(charSequence.toString().getBytes());
        return encode;
}

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
