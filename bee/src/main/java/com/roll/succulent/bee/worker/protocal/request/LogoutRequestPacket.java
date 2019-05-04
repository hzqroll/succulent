package com.roll.succulent.bee.worker.protocal.request;

import com.roll.succulent.bee.worker.protocal.Packet;
import com.roll.succulent.bee.worker.protocal.command.Command;

/**
 * @author zongqiang.hao
 * created on 2019-04-30 09:01.
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
