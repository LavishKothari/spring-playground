package com.jmx.trial.dummybeans;

import org.springframework.jmx.export.naming.SelfNaming;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class Person implements PersonMBean, SelfNaming {
    private String name;
    private int age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public ObjectName getObjectName() throws MalformedObjectNameException {
        return new ObjectName("custom.bean:name=samplePersonBean");
    }
}
