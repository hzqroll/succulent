package com.roll.succulent.bee.worker.client.console.impl;

import com.roll.succulent.bee.worker.client.console.ConsoleCommand;
import com.roll.succulent.bee.worker.protocal.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zongqiang.hao
 * created on 2019-04-30 08:54.
 */
public class CreateGroupCommandManager implements ConsoleCommand {

    private static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Channel channel, Scanner scanner) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String groupId = scanner.nextLine();
        createGroupRequestPacket.setUserIdList(Arrays.asList(groupId.split(USER_ID_SPLIT)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
