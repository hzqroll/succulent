package com.roll.succulent.bee.worker.protocal;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author zongqiang.hao
 * created on 2019-04-19 17:57.
 */
public abstract class Packet {
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(deserialize = false, serialize = false)
    public abstract byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
