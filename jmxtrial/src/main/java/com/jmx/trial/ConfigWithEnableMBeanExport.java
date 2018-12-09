package com.jmx.trial;

import com.jmx.trial.dummybeans.ManagedPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.MBeanServerFactoryBean;

@Configuration
@EnableMBeanExport(server = "server")
public class ConfigWithEnableMBeanExport {

    @Bean
    public ManagedPerson managedPersonBean() {
        ManagedPerson person = new ManagedPerson();
        person.setAge(25);
        person.setName("Lavish");
        return person;
    }

    @Bean
    public MBeanServerFactoryBean server() {
        return new MBeanServerFactoryBean();
    }

}
