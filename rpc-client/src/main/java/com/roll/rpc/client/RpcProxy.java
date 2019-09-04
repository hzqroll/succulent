package com.roll.rpc.client;

import com.roll.rpc.common.bean.RpcRequest;
import com.roll.rpc.common.bean.RpcResponse;
import com.roll.rpc.register.ServerDiscovery;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author roll
 * created on 2019-09-03 20:07
 */
public class RpcProxy {
    private String serviceAddress;

    private ServerDiscovery serviceDiscovery;

    public RpcProxy(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcProxy(ServerDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T create(final Class<?> interfaceClass) {
        return create(interfaceClass, "");
    }

    public <T> T create(final Class<?> interfaceClass, final String serviceVersion) {
        // create proxy object
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    // create rpc request object and set attribute
                    RpcRequest request = new RpcRequest();
                    request.setRequestId(UUID.randomUUID().toString());
                    request.setInterfaceName(method.getDeclaringClass().getName());
                    request.setServiceVersion(serviceVersion);
                    request.setMethodName(method.getName());
                    request.setParameterTypes(method.getParameterTypes());
                    request.setParameters(args);
                    // get rpc address
                    if (serviceDiscovery != null) {
                        String serviceName = interfaceClass.getName();
                        if (serviceVersion != null && serviceVersion.length() > 0) {
                            serviceName += "-" + serviceVersion;
                        }
                        serviceAddress = serviceDiscovery.discovery(serviceName);
                        System.out.println("discovery service:" + serviceName + "->" + serviceAddress);
                    }
                    if (serviceAddress == null || serviceAddress.length() == 0) {
                        throw new RuntimeException("server address is empty");
                    }
                    // get address and port from rpc serviceAddress
                    String[] array = StringUtils.split(serviceAddress, ":");
                    String host = array[0];
                    int port = Integer.valueOf(array[1]);
                    // create rpc client object and send
                    RpcClient client = new RpcClient(host, port);
                    RpcResponse response = client.send(request);
                    if (request == null) {
                        throw new RuntimeException("response is null");
                    }
                    if (response.getException() != null) {
                        throw response.getException();
                    } else {
                        return response.getResult();
                    }
                }
        );
    }

}
