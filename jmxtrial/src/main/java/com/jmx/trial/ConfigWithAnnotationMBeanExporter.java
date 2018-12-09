package com.jmx.trial;

import com.jmx.trial.dummybeans.ManagedPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import javax.management.MBeanServer;

@Configuration
public class ConfigWithAnnotationMBeanExporter {

    @Bean
    public MBeanServerFactoryBean server() {
        return new MBeanServerFactoryBean();
    }

    @Bean
    public AnnotationMBeanExporter exporter(MBeanServer server) {
        AnnotationMBeanExporter mBeanExporter = new AnnotationMBeanExporter();
        mBeanExporter.setServer(server);
        return mBeanExporter;
    }

    @Bean
    public ManagedPerson managedPersonBean() {
        ManagedPerson person = new ManagedPerson();
        person.setAge(25);
        person.setName("Lavish");
        return person;
    }
}
