package com.roll.succulent.bee.worker.protocal.request;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.command.Command;

/**
 * @author zongqiang.hao
 * created on 2019-04-25 23:10.
 */
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
