package com.roll.succulentflower.demos.protocal.command;

/**
 * 是同学过程中java对象的抽象类
 *
 * @author zongqiang.hao
 * created on 2018-12-16 16:05.
 */
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     *
     * @return 返回指令信息
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
