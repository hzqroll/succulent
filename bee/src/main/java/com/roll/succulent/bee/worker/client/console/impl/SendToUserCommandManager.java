package com.roll.succulent.bee.worker.client.console.impl;

import com.roll.succulent.bee.worker.client.console.ConsoleCommand;
import com.roll.succulent.bee.worker.protocal.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zongqiang.hao
 * created on 2019-05-04 11:00.
 */
public class SendToUserCommandManager implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.print("发送消息给某个某个用户：");

        String userId = scanner.nextLine();
        String message = scanner.nextLine();

        channel.writeAndFlush(new MessageRequestPacket(userId, message));
    }
}
