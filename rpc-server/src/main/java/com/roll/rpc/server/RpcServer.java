package com.roll.rpc.server;

import com.roll.rpc.common.bean.RpcRequest;
import com.roll.rpc.common.bean.RpcResponse;
import com.roll.rpc.common.codec.RpcDecoder;
import com.roll.rpc.common.codec.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import javax.imageio.spi.ServiceRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * RPC 服务器(用于发布RCP服务)
 *
 * @author roll
 * created on 2019-09-03 16:25
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {


    private String serviceAddress;

    private ServiceRegistry serviceRegistry;

    public RpcServer(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcServer(String serviceAddress, ServiceRegistry serviceRegistry) {
        this.serviceAddress = serviceAddress;
        this.serviceRegistry = serviceRegistry;
    }


    /**
     * 存放服务名与服务对象之间的映射关系
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(workerGroup, bossGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new RpcDecoder(RpcRequest.class)); // 解码 RPC 请求
                pipeline.addLast(new RpcEncoder(RpcResponse.class)); // 编码 RPC 响应
                pipeline.addLast(new RpcServerHandler(handlerMap)); // 处理 RPC 请求
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // 扫描带有 RPCService注解的类并初始化 handlerMap对象
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        if (serviceBeanMap.size() > 0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String serviceName = rpcService.value().getName();
                String serviceVersion = rpcService.version();

                if (!StringUtils.isEmpty(serviceVersion)) {
                    serviceName += "-" + serviceVersion;
                }
                handlerMap.put(serviceName, serviceBean);
            }
        }
    }
}
