package com.roll.succulent.bee.worker.protocal.request;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.command.Command;

/**
 * @author zongqiang.hao
 * created on 2019-04-25 08:33.
 */
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
