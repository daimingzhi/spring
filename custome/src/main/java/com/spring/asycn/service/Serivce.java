package com.spring.asycn.service;

/**
 * @author dmz
 * @date Create in 16:33 2019/3/25
 */
public class Serivce {
    private int a = 10;

    private Demo demo;

    public Serivce(Demo demo){
        this.demo = demo;
    }

    public void show() {
        System.out.println(a);
    }
}
