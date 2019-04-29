package com.roll.succulent.bee.worker.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 08:38.
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Attribute<Boolean> loginAttr = ctx.channel().attr(AttributeKey.valueOf("login"));
        if (loginAttr.get() == null) {
            System.out.println("用户未登录");
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Attribute<Boolean> loginAttr = ctx.channel().attr(AttributeKey.valueOf("login"));
        if (loginAttr.get() != null && loginAttr.get()) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
