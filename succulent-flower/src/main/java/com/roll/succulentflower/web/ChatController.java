package com.roll.succulentflower.web;

        import com.alibaba.fastjson.JSON;
        import com.roll.succulentflower.chat.MessageService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongqiang.hao
 * created on 2018/11/19 8:40 PM.
 */
@RestController("/chat")
public class ChatController {

    @Autowired
    private MessageService messageService;

    public String getChat() {
        return JSON.toJSONString(messageService.getChats());
    }
}
