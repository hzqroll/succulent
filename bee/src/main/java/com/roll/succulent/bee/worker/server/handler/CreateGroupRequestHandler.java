package com.roll.succulent.bee.worker.server.handler;

import com.roll.succulent.bee.worker.protocal.request.CreateGroupRequestPacket;
import com.roll.succulent.bee.worker.protocal.response.CreateGroupResponsePacket;
import com.roll.succulent.bee.worker.util.IDUtil;
import com.roll.succulent.bee.worker.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2019-04-30 17:48.
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();

        List<String> userNameList = new ArrayList<>();

        // 1. 创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        CreateGroupResponsePacket groupResponsePacket = new CreateGroupResponsePacket();
        groupResponsePacket.setUserNameList(userNameList);
        groupResponsePacket.setSuccess(true);
        groupResponsePacket.setGroupId(IDUtil.randomId());
        channelGroup.writeAndFlush(groupResponsePacket);

        System.out.print("群创建成功，id 为[" + groupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + groupResponsePacket.getUserNameList());
    }
}
