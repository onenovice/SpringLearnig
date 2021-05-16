package com.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 注解开发AOP，切面
 */
@Component("myAspect")
@Aspect//标注当前MyAspect是一个切面类
public class MyAspect {
    //配置前置通知
    //@Before("execution(* com.anno.*.*(..))")这种方式或下面一种
    //@Before("pointcut()")//其他注解也是如此
    @Before("MyAspect.pointcut()")
    public void before() {
        System.out.println("前置增强...");
    }

    @AfterReturning("execution(* com.anno.*.*(..))")
    public void afterReturning() {
        System.out.println("后置增强...");
    }

    @Around("execution(* com.anno.*.*(..))")
    //Proceeding JoinPoint:正在执行的连接点，即切点
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("环绕前增强...");
        Object proceed = pjp.proceed();//切点方法
        System.out.println("环绕后增强...");

        return proceed;
    }

    //异常抛出
    @AfterThrowing("execution(* com.anno.*.*(..))")
    public void afterThrowing() {
        System.out.println("异常抛出增强...");
    }

    //最终增强:无论是否存在异常都执行
    @After("execution(* com.anno.*.*(..))")
    public void after() {
        System.out.println("最终增强...");
    }

    //定义切点表达式
    @Pointcut("execution(* com.anno.*.*(..))")
    public void pointcut() {
    }
}
