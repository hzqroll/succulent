package com.roll.succulent.bee.worker.server;

import com.roll.succulent.bee.worker.protocal.request.MessageRequestPacket;
import com.roll.succulent.bee.worker.protocal.request.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 21:34.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("收到消息: " + messageRequestPacket.getMessage() + " , 回复: get it.");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
