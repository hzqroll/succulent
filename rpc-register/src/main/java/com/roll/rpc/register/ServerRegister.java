package com.roll.rpc.register;

/**
 * 服务注册服务
 *
 * @author roll
 * created on 2019-09-03 15:24
 */
public interface ServerRegister {

    /**
     * 注册服务地址和名称
     *
     * @param serviceAddress 服务地址
     * @param ServiceNam     服务名称
     */
    void register(String ServiceNam, String serviceAddress);
}
