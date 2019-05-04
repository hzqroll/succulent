package com.roll.succulent.bee.worker.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 22:34.
 */
public interface ConsoleCommand {

    /**
     * 执行控制台输入数据
     *
     * @param channel channel
     * @param scanner 控制台输入
     */
    void exec(Channel channel, Scanner scanner);
}
