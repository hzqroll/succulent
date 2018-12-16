package com.roll.succulent.controller;

import org.springframework.stereotype.Controller;

/**
 * @author zongqiang.hao
 * created on 2018/11/17 9:40 PM.
 */
@Controller("/send")
public class SendMessageController {

    public String sendMessage(String message){
        return "hi";
    }
}
