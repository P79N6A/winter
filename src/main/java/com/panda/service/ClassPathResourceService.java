package com.panda.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author: 青铜
 * @create: 2018-05-27
 **/
public class ClassPathResourceService {

    public static void main(String[] args) {
        //类路径包括lib里jar包的资源 详见 System.getProperty("java.class.path")
        Resource resource = new ClassPathResource("META-INF/spring.handlers");
        try {
            if (resource.exists())
                System.out.println(resource.getURL().toString());
            //jar:file:/Users/zhanghui/.m2/repository/org/springframework/spring-beans/4.3.8.RELEASE
            // /spring-beans-4.3.8.RELEASE.jar!/META-INF/spring.handlers
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
