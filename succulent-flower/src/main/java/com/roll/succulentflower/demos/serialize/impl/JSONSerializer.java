package com.roll.succulentflower.demos.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.roll.succulentflower.demos.serialize.Serializer;
import com.roll.succulentflower.demos.serialize.SerializerAlgorithm;

/**
 * @author zongqiang.hao
 * created on 2018-12-16 16:25.
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializeAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
