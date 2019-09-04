package com.roll.rpc.register.zookeeper;

import com.roll.rpc.register.ServerRegister;
import org.I0Itec.zkclient.ZkClient;


/**
 * @author roll
 * created on 2019-09-03 15:32
 */
public class ZooKeeperServiceRegister implements ServerRegister {

    // zk 客户端
    private final ZkClient zkClient;

    public ZooKeeperServiceRegister(String zkAddress) {
        // 创建 ZooKeeper 客户端
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        System.out.println("connect zookeeper");
    }


    @Override
    public void register(String serviceName, String serviceAddress) {
        // 创建 register 节点(持久)
        String registeryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registeryPath)) {
            zkClient.createPersistent(registeryPath);
            System.out.println("create register node: " + registeryPath);
        }

        // 创建 service 节点(永久)
        String servicePath = registeryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            System.out.println("create service node: " + servicePath);
        }

        // 创建address节点(临时)
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        System.out.println("create address node: " + addressNode);
    }
}
