package com.roll.succulent.bee.worker.code;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.PacketCodeC;
import com.roll.succulent.bee.worker.protocal.request.CreateGroupRequestPacket;
import com.roll.succulent.bee.worker.protocal.request.LoginRequestPacket;
import com.roll.succulent.bee.worker.protocal.request.LogoutRequestPacket;
import com.roll.succulent.bee.worker.protocal.response.CreateGroupResponsePacket;
import com.roll.succulent.bee.worker.protocal.response.LoginResponsePacket;
import com.roll.succulent.bee.worker.protocal.request.MessageRequestPacket;
import com.roll.succulent.bee.worker.protocal.response.LogoutResponsePacket;
import com.roll.succulent.bee.worker.protocal.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 21:13.
 */
public class Decode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            list.add(loginRequestPacket);
        } else if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            list.add(loginResponsePacket);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            list.add(messageRequestPacket);
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            list.add(messageResponsePacket);
        } else if (packet instanceof CreateGroupResponsePacket) {
            CreateGroupResponsePacket groupResponsePacket = (CreateGroupResponsePacket) packet;
            list.add(groupResponsePacket);
        } else if (packet instanceof CreateGroupRequestPacket) {
            CreateGroupRequestPacket groupRequestPacket = (CreateGroupRequestPacket) packet;
            list.add(groupRequestPacket);
        } else if (packet instanceof LogoutRequestPacket) {
            LogoutRequestPacket logoutRequestPacket = (LogoutRequestPacket) packet;
            list.add(logoutRequestPacket);
        } else if (packet instanceof LogoutResponsePacket) {
            LogoutResponsePacket logoutResponsePacket = (LogoutResponsePacket) packet;
            list.add(logoutResponsePacket);
        }
    }
}
