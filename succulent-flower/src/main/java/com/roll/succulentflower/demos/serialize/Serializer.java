package com.roll.succulentflower.demos.serialize;

import com.roll.succulentflower.demos.serialize.impl.JSONSerializer;

/**
 * @author zongqiang.hao
 * created on 2018-12-16 16:22.
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializeAlgorithm();

    /**
     * java对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成java对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);


}
