package com.proxy.jdk;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 9:28 2021/5/11
 * @ Version:
 */
public class Advice {
    public void before() {
        System.out.println("前置增强...");
    }

    public void after() {
        System.out.println("后置增强...");
    }
}
