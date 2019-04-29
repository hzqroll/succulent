package com.roll.succulent.bee.worker.client;

import com.roll.succulent.bee.worker.code.Decode;
import com.roll.succulent.bee.worker.code.Encode;
import com.roll.succulent.bee.worker.protocal.PacketCodeC;
import com.roll.succulent.bee.worker.protocal.request.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author zongqiang.hao
 * created on 2019-04-25 09:01.
 */
public class LoginClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9001;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new Decode());
                        ch.pipeline().addLast(new LoginHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new Encode());
                    }
                })
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功!");
                // 连接成功后发送消息
                new Thread(() -> {
                    ChannelFuture channelFuture = (ChannelFuture) future;
                    if (channelFuture.channel().attr(AttributeKey.valueOf("login")) != null) {
                        //boolean loginFLag = (boolean) channelFuture.channel().attr(AttributeKey.valueOf("login")).get();
                        while (true) {
                            System.out.println("连接成功后发送消息到服务端");
                            Scanner scanner = new Scanner(System.in);
                            String message = scanner.nextLine();
                            MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                            messageRequestPacket.setMessage(message);
                            channelFuture.channel().writeAndFlush(messageRequestPacket);
                        }
                    }
                }).start();
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println("error: " + future.get());
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
