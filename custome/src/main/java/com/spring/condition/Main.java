package com.spring.condition;

import com.spring.condition.config.Config;
import com.spring.condition.domain.Father;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:38 2019/3/22
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        Father father = annotationConfigApplicationContext.getBean(Father.class);
        Environment environment = annotationConfigApplicationContext.getBean(Environment.class);
        String who = environment.getProperty("who");
        System.out.println(father.getWho());
        System.out.println(who);
        //Mother mother = annotationConfigApplicationContext.getBean(Mother.class);
        System.out.println(father);
        //System.out.println(mother);
    }

}
