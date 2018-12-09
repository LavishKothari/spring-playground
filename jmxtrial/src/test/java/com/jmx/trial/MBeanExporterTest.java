package com.jmx.trial;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.test.annotation.DirtiesContext;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

@DirtiesContext // you will need this as in every method you are creating ApplicationContext
public class MBeanExporterTest {

    @Test
    public void testSimpleExporting() throws Exception {
        testBeanExportedWithXml("simple-bean-without-interface.xml",
                "server", "com.mybean:name=testBean1");
    }

    @Test
    public void testBeanExporterToMBeanServer() throws Exception {
        testBeanExportedWithXml("basic-autodetect.xml",
                "server", "custom.bean:name=samplePersonBean");
    }

    @Test
    public void testExporterWithAssembler() throws Exception {
        testBeanExportedWithXml("exporter-with-assembler.xml",
                "server", "com.jmx.trial:name=managedPerson");
    }
    @Test
    public void testWithMBeanExportInXML() throws Exception {
        testBeanExportedWithXml("mbean-export.xml",
                "server", "com.jmx.trial:name=managedPerson");
    }

    @Test
    public void testAnnotationMBeanExporter() throws Exception {
        testBeanExportedWithXml("annotation-mbean-exporter.xml",
                "server", "com.jmx.trial:name=managedPerson");
    }

    @Test
    public void testConfigWithAnnotationMBeanExporter() throws Exception {
        testBeanExportedWithConfigClass(ConfigWithAnnotationMBeanExporter.class,
                "server", "com.jmx.trial:name=managedPerson");
    }


    @Test
    public void testConfigWithEnableMBeanExport() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigWithEnableMBeanExport.class);
        MBeanExporter mBeanExporter = (MBeanExporter) context.getBean("mbeanExporter");

        MBeanServer mBeanServer = mBeanExporter.getServer();

        ObjectInstance on = (ObjectInstance) mBeanServer.getObjectInstance(new ObjectName("com.jmx.trial:name=managedPerson"));
        Assert.assertNotNull("the exported bean is null", on);
    }

    private void testBeanExportedWithConfigClass(Class<?> clazz, String mBeanServerName, String objectName)
            throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(clazz);
        checkExportOnServerSuccessful(context, mBeanServerName, objectName);
    }

    private void testBeanExportedWithXml(String configFile, String mBeanServerName, String objectName) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(configFile);
        checkExportOnServerSuccessful(context, mBeanServerName, objectName);
    }

    private void checkExportOnServerSuccessful(ApplicationContext context, String mBeanServerName, String objectName)
            throws Exception {
        MBeanServer mBeanServer = (MBeanServer) context.getBean(mBeanServerName);
        ObjectInstance on = (ObjectInstance) mBeanServer.getObjectInstance(new ObjectName(objectName));
        Assert.assertNotNull("the exported bean is null", on);
    }


}
