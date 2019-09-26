package com.roll.succulentflower.queenbee;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author roll
 * created on 2019-09-25 08:39
 */
@Service
public class QueenBeeServer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new InitializerHandler());


        //bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        //bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

        try {
            bootstrap.bind(9021);

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
