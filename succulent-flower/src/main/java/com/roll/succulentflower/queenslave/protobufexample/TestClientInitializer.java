package com.roll.succulentflower.queenslave.protobufexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author zongqiang.hao
 * created on 2019-06-10 23:31.
 */
public class TestClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
        channelPipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast(new ProtobufEncoder());

        channelPipeline.addLast(new TestClientHandler());
    }
}
