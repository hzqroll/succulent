package com.roll.succulentflower.queenslave.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zongqiang.hao
 * created on 2018/11/18 1:06 PM.
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    public MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        messageService.getMessage(msg);
    }
}
