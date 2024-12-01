package com.example.aop.bug.demo;

import org.springframework.stereotype.Component;

@Component
public class DemoBean {
    public void sayHello() {
        System.out.println("hello");
    }
}
