package com.aop;

/**
 *
 */
public class Target implements TargetInterface {
    @Override
    public void save() {

        System.out.println("save running...");
        //int i=1/0;//为了测试抛出异常增强
    }
}
