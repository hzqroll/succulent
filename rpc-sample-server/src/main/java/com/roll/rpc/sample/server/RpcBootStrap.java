package com.roll.rpc.sample.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author roll
 * created on 2019-09-03 15:04
 */
public class RpcBootStrap {

    public static void main(String[] args) {
        System.out.println("start server");
        new ClassPathXmlApplicationContext("classpath:rpc-sample-server.xml");
    }
}
