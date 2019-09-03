package com.roll.rpc.server;

import com.roll.rpc.common.bean.RpcRequest;
import com.roll.rpc.common.bean.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author roll
 * created on 2019-09-03 19:35
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private final Map<String, Object> handleMap;

    public RpcServerHandler(Map<String, Object> handleMap) {
        this.handleMap = handleMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        // 创建并初始化 RPC 响应对象
        RpcResponse response = new RpcResponse();
        response.setRequestId(msg.getRequestId());

        try {
            Object result = handle(msg);
            response.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        // 写入RPC响应对象并自动管理连接
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private Object handle(RpcRequest request) throws InvocationTargetException {
        // 获取服务对象
        String serviceName = request.getInterfaceName();
        String serviceVersion = request.getServiceVersion();
        if (!StringUtils.isEmpty(serviceVersion)) {
            serviceName += "-" + serviceVersion;
        }

        Object serviceBean = handleMap.get(serviceName);
        if (serviceBean == null) {
            throw new RuntimeException("can not find service bean by key: " + serviceName);
        }

        //获取反射调用所需的参数
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server caught exception.");
        cause.printStackTrace();
        ctx.close();
    }
}
