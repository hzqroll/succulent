package com.roll.succulent.bee.worker.client;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.PacketCodeC;
import com.roll.succulent.bee.worker.protocal.request.LoginRequestPacket;
import com.roll.succulent.bee.worker.protocal.request.LoginResponsePacket;
import com.roll.succulent.bee.worker.protocal.request.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.UUID;

/**
 * @author zongqiang.hao
 * created on 2019-04-19 17:44.
 */
public class ClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                // 设置登陆成功标记位
                ctx.channel().attr(AttributeKey.valueOf("login")).set(true);
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println("收到服务端消息: " + messageResponsePacket.getMessage());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登陆.");
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("name");
        loginRequestPacket.setPassword("pwd");

        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(buf);
    }
}
