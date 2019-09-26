package com.roll.succulent.bee.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author roll
 * created on 2019-09-26 08:45
 */
public class ClientMessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端返回：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String a = "hello";
        ctx.channel().writeAndFlush(a);
        super.channelActive(ctx);
    }
}
