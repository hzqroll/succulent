package com.roll.succulentflower.chat;

import java.io.Serializable;

/**
 * @author zongqiang.hao
 * created on 2018/11/18 1:07 PM.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 140837953698127662L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
