package com.jmx.trial.dummybeans;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "com.jmx.trial:name=managedPerson", description = "sample managed person bean")
public class ManagedPerson {
    private String name;
    private int age;

    @ManagedOperation(description = "gives the name of the person")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
