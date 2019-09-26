package com.roll.succulent.bee.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author roll
 * created on 2019-09-26 08:38
 */
@Component
public class BeeClient implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ClientInitializerHandler());
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        Channel channel = bootstrap.connect("127.0.0.1", 9021).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
