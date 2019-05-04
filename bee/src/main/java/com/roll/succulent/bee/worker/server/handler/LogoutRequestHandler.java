package com.roll.succulent.bee.worker.server.handler;

import com.roll.succulent.bee.worker.protocal.request.LogoutRequestPacket;
import com.roll.succulent.bee.worker.protocal.response.LogoutResponsePacket;
import com.roll.succulent.bee.worker.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zongqiang.hao
 * created on 2019-05-04 11:43.
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
