package com.roll.succulent.bee.worker.client;

import com.roll.succulent.bee.worker.protocal.request.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 20:45.
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        System.out.println("收到服务端消息: " + messageResponsePacket.getMessage());
    }
}
