package com.roll.succulent.bee.worker.server;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 21:37.
 */
public class Code extends ByteToMessageCodec<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        PacketCodeC.INSTANCE.decode(byteBuf);
    }
}
