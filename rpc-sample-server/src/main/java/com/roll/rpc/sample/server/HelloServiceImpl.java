package com.roll.rpc.sample.server;

import com.roll.rpc.server.RpcService;
import com.roll.succulent.sample.HelloService;
import com.roll.succulent.sample.Person;

/**
 * @author roll
 * created on 2019-09-03 14:44
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }

    @Override
    public String hello(Person person) {
        return "hello" + person.toString();
    }
}
