package com.roll.succulentflower;

import com.roll.succulentflower.demos.protocal.command.LoginRequestPacket;
import com.roll.succulentflower.demos.protocal.command.Packet;
import com.roll.succulentflower.demos.protocal.command.PacketCodeC;
import com.roll.succulentflower.demos.serialize.Serializer;
import com.roll.succulentflower.demos.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {
    @Test
    public void encode() {

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setPassWord("password");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }
}
