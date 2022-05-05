package com.test;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class    Main {
    public static void main(String[] args) throws LifecycleException {
        //使用嵌入式方法启动tomcat，方便调试
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("port",8080));
        tomcat.getConnector();
        //创建webapp
        Context context = tomcat.addWebapp("",new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resource = new StandardRoot(context);
        resource.addPreResources(
                new DirResourceSet(resource,"/WEB-INF/classes",new File("target/classes").getAbsolutePath(),"/")
        );
        context.setResources(resource);
        tomcat.start();
        tomcat.getServer().await();
    }
}
