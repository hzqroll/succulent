package com.roll.succulent.bee.worker.protocal.response;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.command.Command;

/**
 * @author zongqiang.hao
 * created on 2019-04-25 23:11.
 */
public class MessageResponsePacket extends Packet {

    /**
     * 发送方 userId
     */
    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
