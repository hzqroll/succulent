package com.roll.succulent.bee.worker.session;

import io.netty.util.AttributeKey;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 15:53.
 */
public class Session {
    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static AttributeKey<Session> getSessionAttr() {
        return AttributeKey.valueOf("session");
    }

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
