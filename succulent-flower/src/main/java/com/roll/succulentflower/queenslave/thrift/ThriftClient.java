package com.roll.succulentflower.queenslave.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author zongqiang.hao
 * created on 2019-06-12 08:55.
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost", 8899), 600);

        TProtocol protocol = new TCompactProtocol(tTransport);
        try {
            tTransport.open();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            tTransport.close();
        }
    }
}
