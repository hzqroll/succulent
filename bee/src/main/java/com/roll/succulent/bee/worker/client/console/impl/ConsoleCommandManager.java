package com.roll.succulent.bee.worker.client.console.impl;


import com.roll.succulent.bee.worker.client.console.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 22:36.
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new ConcurrentHashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserCommandManager());
        consoleCommandMap.put("logout", new LogoutConsoleManager());
        consoleCommandMap.put("createGroup", new CreateGroupCommandManager());
        consoleCommandMap.put("login", new LoginConsoleCommandManager());
    }

    @Override
    public void exec(Channel channel, Scanner scanner) {
        String command = scanner.nextLine();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(channel, scanner);
        } else {
            System.err.println("无法识别的指令: " + command + ", 请重新输入!");
        }
    }
}
