package com.web;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 10:59 2021/5/13
 * @ Version:
 */
public class WebApplicationContextUtils {
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        return (ApplicationContext) servletContext.getAttribute("app");

    }
}
