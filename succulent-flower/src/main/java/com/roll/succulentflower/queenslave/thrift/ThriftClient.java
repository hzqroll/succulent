package com.roll.succulentflower.queenslave.thrift;

import com.roll.succulentflower.queenslave.thrift.thrift.generated.Person;
import com.roll.succulentflower.queenslave.thrift.thrift.generated.PersonService;
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

        PersonService.Client client = new PersonService.Client(protocol);
        try {
            tTransport.open();

            Person person = client.getPersonByUserName("张三");

            System.out.println(person.getUserName());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());
            System.out.println("------");

            Person person1 = new Person();
            person.setUserName("lisi");
            person.setAge(40);
            person.setMarried(true);
            client.savePerson(person);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            tTransport.close();
        }
    }
}
