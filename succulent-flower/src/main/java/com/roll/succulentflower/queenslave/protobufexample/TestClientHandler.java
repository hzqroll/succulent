package com.roll.succulentflower.queenslave.protobufexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author zongqiang.hao
 * created on 2019-06-10 23:34.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (randomInt == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(
                            MyDataInfo.MyMessage.DataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().
                            setName("张三").
                            setAge(20).
                            setAddress("背景").build()
                    ).build();
        } else if (randomInt == 1) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(
                            MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().
                            setName("一只狗").
                            setAge(4).build()
                    ).build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(
                            MyDataInfo.MyMessage.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().
                            setName("一只猫").setCity("杭州").build()
                    ).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
