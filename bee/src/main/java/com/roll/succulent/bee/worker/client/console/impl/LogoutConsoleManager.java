package com.roll.succulent.bee.worker.client.console.impl;

import com.roll.succulent.bee.worker.client.console.ConsoleCommand;
import com.roll.succulent.bee.worker.protocal.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zongqiang.hao
 * created on 2019-05-04 10:58.
 */
public class LogoutConsoleManager implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
