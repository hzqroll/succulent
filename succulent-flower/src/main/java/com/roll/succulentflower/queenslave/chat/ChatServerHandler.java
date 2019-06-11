package com.roll.succulentflower.queenslave.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zongqiang.hao
 * created on 2018/11/20 8:38 PM.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * Is called for each message of type {@link }.
     * 每次接收到数据时，都会调用这个方法。需要注意的是，油服务器发送的消息可能会被反馈接收
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server reciver: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
     * to the next { ChannelHandler} in the { ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     * 通知ChanddelInoudHandler最后一次对ChannelRead()的调用时当前批量读取中的最后一条消息
     *
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next  ChannelHandler} in the  ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
