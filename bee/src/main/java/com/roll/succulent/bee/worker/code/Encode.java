package com.roll.succulent.bee.worker.code;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zongqiang.hao
 * created on 2019-04-27 21:21.
 */
public class Encode extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
