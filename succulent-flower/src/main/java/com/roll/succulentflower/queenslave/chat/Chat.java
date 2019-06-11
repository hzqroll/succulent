package com.roll.succulentflower.queenslave.chat;

import java.io.Serializable;

/**
 * @author zongqiang.hao
 * created on 2018/11/18 1:07 PM.
 */
public class Chat implements Serializable {
    private static final long serialVersionUID = -7127475225638440016L;
    /**
     * 消息发送者
     */
    private User user;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "user=" + user +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
