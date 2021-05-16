package com.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 要求看到能懂(被Spring集成到内部，简化了)
 */
public class ProxyTest {
    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //获得增强对象
        final Advice advice = new Advice();
        //返回值是动态代理生成的代理对象
        //1.创建增强器
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(Target.class);
        //3.设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                advice.before();//执行前置

                Object invoke = method.invoke(target, args);

                advice.after();//执行后置
                return invoke;
            }
        });
        //4.创建代理对象
        Target proxy = (Target) enhancer.create();
        //调用代理对象方法
        proxy.save();
    }
}
