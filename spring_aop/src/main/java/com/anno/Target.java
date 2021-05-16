package com.anno;

/**
 * 注解开发AOP
 */

import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {
    @Override
    public void save() {

        System.out.println("save running...");
        //int i=1/0;//为了测试抛出异常增强
    }
}
