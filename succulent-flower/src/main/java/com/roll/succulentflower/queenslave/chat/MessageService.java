package com.roll.succulentflower.queenslave.chat;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zongqiang.hao
 * created on 2018/11/19 8:19 PM.
 */
@Service
public class MessageService {

    private List<Chat> chats = new CopyOnWriteArrayList<>();

    /**
     * 获取消息
     */
    public void getMessage(String message) {
        chats.add(JSONObject.parseObject(message, Chat.class));
    }

    public List<Chat> getChats() {
        return chats;
    }
}
