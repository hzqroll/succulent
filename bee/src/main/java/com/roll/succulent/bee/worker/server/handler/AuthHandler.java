package com.roll.succulent.bee.worker.server.handler;

import com.roll.succulent.bee.worker.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 08:38.
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("用户未登录");
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
        }
    }
}
