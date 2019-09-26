package com.roll.succulentflower.queenbee;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author roll
 * created on 2019-09-25 09:01
 */
public class MessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端消息：" + s);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接：" + ctx.channel().localAddress());
        super.channelRegistered(ctx);
    }
}
