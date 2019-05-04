package com.roll.succulent.bee.worker.client.console.impl;

import com.roll.succulent.bee.worker.client.console.ConsoleCommand;
import com.roll.succulent.bee.worker.protocal.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zongqiang.hao
 * created on 2019-04-30 17:12.
 */
public class LoginConsoleCommandManager implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.print("输入用户名登录: ");
        String userName = scanner.nextLine();
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
