package com.resolver;

import com.exception.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 */
public class MyExceptionResolver implements HandlerExceptionResolver {

    //关键参数Exception:异常对象
    //返回值：跳转视图信息
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView=new ModelAndView();
        if(e instanceof MyException){
            modelAndView.addObject("info","自定义异常！");
        }else if(e instanceof ClassCastException){
            modelAndView.addObject("info","类转换异常！");
        }
        modelAndView.setViewName("error1");
        return modelAndView;
    }
}
