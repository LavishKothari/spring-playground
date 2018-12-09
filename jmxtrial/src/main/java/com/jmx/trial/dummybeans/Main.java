package com.jmx.trial.dummybeans;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("basic-autodetect.xml");

//        Person p = (Person) context.getBean("personBean");
//        System.out.println(p.getName());
//        System.out.println(p.getAge());

        logger.debug("Started, now waiting");
        Thread.sleep(Long.MAX_VALUE);
    }
}
