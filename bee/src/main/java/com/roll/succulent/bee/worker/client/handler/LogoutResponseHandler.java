package com.roll.succulent.bee.worker.client.handler;

import com.roll.succulent.bee.worker.protocal.response.LogoutResponsePacket;
import com.roll.succulent.bee.worker.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zongqiang.hao
 * created on 2019-05-04 11:08.
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
