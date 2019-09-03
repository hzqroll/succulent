package com.roll.rpc.register;

/**
 * 服务发现接口
 *
 * @author roll
 * created on 2019-09-03 15:19
 */
public interface ServerDiscovery {

    /**
     * 根据服务名称,查找服务地址
     *
     * @param serviceName 服务名称
     * @return 服务地址
     */
    String discovery(String serviceName);
}
