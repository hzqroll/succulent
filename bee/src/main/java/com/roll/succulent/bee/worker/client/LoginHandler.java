package com.roll.succulent.bee.worker.client;

import com.roll.succulent.bee.worker.protocal.request.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.Date;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 21:30.
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.isSuccess()) {
            // 设置登陆成功标记位
            ctx.channel().attr(AttributeKey.valueOf("login")).set(true);
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
