package com.roll.succulentflower.demos.protocal.command;

import lombok.Data;

/**
 * 登陆请求数据吧继承自packet,然后定义了三个字段
 * @author zongqiang.hao
 * created on 2018-12-16 16:17.
 */
@Data
public class LoginRequestPacket extends Packet implements Command{
    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    private Integer userId;

    private String userName;

    private String passWord;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
