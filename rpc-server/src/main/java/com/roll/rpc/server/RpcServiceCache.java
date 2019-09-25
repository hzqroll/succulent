package com.roll.rpc.server;

import sun.jvm.hotspot.asm.Register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待注册服务缓存
 * 被注册的服务bean缓存
 *
 * @author roll
 * created on 2019-09-06 14:19
 */
public class RpcServiceCache {
    private static final List<Register> registers;

    private static final Map<String, Object> serviceBeanMap;

    static {
        registers = new ArrayList<>();

        serviceBeanMap = new HashMap<>();
    }



}
