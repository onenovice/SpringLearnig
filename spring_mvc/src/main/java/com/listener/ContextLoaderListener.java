package com.listener;

import com.web.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 10:38 2021/5/13
 * @ Version:
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //读取web.xml的全局参数,解耦合
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        ApplicationContext app = new ClassPathXmlApplicationContext(contextConfigLocation);

        //将spring的应用上下文对象存储到ServletContext域中
        servletContext.setAttribute("app",app);

        System.out.println("Spring容器创建完毕！");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
