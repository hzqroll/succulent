package com.roll.succulent.bee.worker.protocal.request;

import com.roll.succulent.bee.worker.protocal.Packet;

/**
 * @author zongqiang.hao
 * created on 2019-04-21 10:54.
 */
public class LoginRequestPacket extends Packet {
    @Override
    public byte getCommand() {
        return 1;
    }

    private String userId;

    private String username;

    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
