package com.roll.succulent.bee.worker.client;

import com.roll.succulent.bee.worker.client.console.impl.ConsoleCommandManager;
import com.roll.succulent.bee.worker.client.console.impl.LoginConsoleCommandManager;
import com.roll.succulent.bee.worker.client.handler.CreateGroupResponseHandler;
import com.roll.succulent.bee.worker.client.handler.LoginHandler;
import com.roll.succulent.bee.worker.client.handler.LogoutResponseHandler;
import com.roll.succulent.bee.worker.client.handler.MessageResponseHandler;
import com.roll.succulent.bee.worker.code.Decode;
import com.roll.succulent.bee.worker.code.Encode;
import com.roll.succulent.bee.worker.server.handler.CreateGroupRequestHandler;
import com.roll.succulent.bee.worker.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                        ch.pipeline().addLast(new Decode());
                        ch.pipeline().addLast(new LoginHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new Encode());
                    }
                })
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                ChannelFuture channelFuture = (ChannelFuture) future;
                startConsoleThread(channelFuture);
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

    private static void startConsoleThread(ChannelFuture channelFuture) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommandManager loginConsoleCommandManager = new LoginConsoleCommandManager();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channelFuture.channel())) {
                    consoleCommandManager.exec(channelFuture.channel(), scanner);
                } else {
                    loginConsoleCommandManager.exec(channelFuture.channel(), scanner);
                }
            }
        }).start();
    }
}
