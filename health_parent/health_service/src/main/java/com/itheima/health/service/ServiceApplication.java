package com.itheima.health.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Date 2021/1/5
 */
public class ServiceApplication {
    public static void main(String[] args) throws IOException, IOException {
        new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        System.in.read();
    }
}
