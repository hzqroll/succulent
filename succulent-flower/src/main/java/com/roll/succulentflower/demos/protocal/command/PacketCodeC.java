package com.roll.succulentflower.demos.protocal.command;

import com.roll.succulentflower.demos.serialize.Serializer;
import com.roll.succulentflower.demos.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.roll.succulentflower.demos.protocal.command.Command.LOGIN_REQUEST;

/**
 * @author zongqiang.hao
 * created on 2018-12-16 17:04.
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializeAlgorithm(), serializer);
    }

    /**
     * 封装成二进制的过程
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码过程
     * 1. 我们假定decode方法传递进来的byuteBuf已经是合法的,即 首四个字节是我们定义的魔数<br>
     * 2. 这里, 我们暂时不关注版本协议,通常我们在没有遇到协议升级的时候,这个字段暂时不处理,<br>
     * 3. 接下来,我们调用ByteBuf的API分别拿到了序列化算法标识,指令,数据包长度<br>
     * 4. 最后, 我们根据拿到的数据包长度取出数据,通过指令拿到改数据包对应的java对象的类型,根据序列化算法标识拿到序列化对象 ,将字节数组转换为java对象
     *
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //序列化算法标识
        byte serializeAlgorathm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorathm);

        if (requestType != null && serializer != null) {
            Packet packet =  serializer.deserialize(requestType, bytes);
            return packet;
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}