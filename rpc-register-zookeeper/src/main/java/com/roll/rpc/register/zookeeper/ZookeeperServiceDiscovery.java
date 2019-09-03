package com.roll.rpc.register.zookeeper;

import com.roll.rpc.register.ServerDiscovery;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @author roll
 * created on 2019-09-03 15:57
 */
public class ZookeeperServiceDiscovery implements ServerDiscovery {

    // zk 客户端
    private final ZkClient zkClient;

    public ZookeeperServiceDiscovery(String zkAddress) {
        // 创建 ZooKeeper 客户端
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        System.out.println("connect zookeeper");
    }

    @Override
    public String discovery(String serviceName) {
        try {
            // 获取service节点
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
            if (!zkClient.exists(servicePath)) {
                throw new RuntimeException("can not find any service node on path: " + servicePath);
            }

            List<String> addresslist = zkClient.getChildren(servicePath);
            if (addresslist == null || addresslist.size() == 0) {
                throw new RuntimeException("can not find any service node on path: " + servicePath);
            }

            // 获取address节点
            String address = addresslist.get(0);
            // 获取address节点的值
            String addressPath = servicePath + "/" + address;
            return zkClient.readData(addressPath);

        } finally {
            zkClient.close();
        }
    }
}
