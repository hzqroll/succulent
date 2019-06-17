package com.roll.succulentflower.queenslave.thrift;

import com.roll.succulentflower.queenslave.thrift.thrift.generated.DataException;
import com.roll.succulentflower.queenslave.thrift.thrift.generated.Person;
import com.roll.succulentflower.queenslave.thrift.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author zongqiang.hao
 * created on 2019-06-12 08:50.
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUserName(String userName) throws DataException, TException {
        System.out.println("Got clent param " + userName);
        Person person = new Person();
        person.setUserName(userName);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("save clent param " + person);
        System.out.println(person.getUserName());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
