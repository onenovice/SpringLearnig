package com.controller;

import com.domain.User;
import com.domain.VO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ Description:

 */
@Controller
//@RequestMapping("/xxx")//和方法前的该注解拼接（从而可以从前缀分辨属于哪个控制器）
public class UserController {

    //请求地址  http://localhost:8080/quik
    @RequestMapping("/quick1")
    public String save(){
        System.out.println("Controller Running ...");
        return "success.jsp";//若类前有 @RequestMapping注解，需要返回 "/success.jsp" ，表示相对地址
    }
    @RequestMapping("/quick2")
    public ModelAndView save2(){
        /*
         Model: 模型  作用：封装数据
         View： 视图  作用：展示数据
         */
        ModelAndView modelAndView=new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","张三");
        //设置视图
        modelAndView.setViewName("index.jsp");
        return modelAndView;
    }
    @RequestMapping("/quick3")
    public ModelAndView save3(ModelAndView modelAndView){//Spring会为之提供对象modelAndView
        modelAndView.addObject("username","法外狂徒张三");
        modelAndView.setViewName("index.jsp");

        return modelAndView;
    }
    @RequestMapping("/quick4")
    public String save4(Model model){//Spring会为之提供对象modelAndView
        model.addAttribute("username","法外狂徒");
        return "index.jsp";
    }
    //非Spring,不常用
    @RequestMapping("/quick5")
    public String save5(HttpServletRequest request){//Spring会为之提供对象modelAndView
        request.setAttribute("username","王五");
        return "index.jsp";
    }
    //回写字符串：直接返回字符串
    @RequestMapping("/quick6")
    public void save6(HttpServletResponse response) throws IOException {//Spring会为之提供对象modelAndView
        response.getWriter().print("hello world!");
    }
    //注意区分视图跳转
    @RequestMapping(value = "/quick7",produces = "text/html;charset=UTF-8")//中文需要指定utf8编码
    @ResponseBody//告知 不是进行页面跳转而是数据响应（重点！！！！）
    public String save7(){
        return "回写字符串。";
    }

    @RequestMapping(value = "/quick8")//中文需要指定utf8编码
    @ResponseBody
    public String save8(){
        return "{\"username\":\"zhangsan\",\"age\":18}";//转义
    }

    @RequestMapping(value = "/quick9")
    @ResponseBody
    public String save9() throws Exception {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(19);
        //使用json的转换工具将对象转换成json格式字符串返回（需要导入相关三个依赖）
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return json;
    }

    //返回对象或集合
    @RequestMapping(value = "/quick10")
    @ResponseBody
    //期望SpringMVC自动将User对象转换成json格式字符串，需要在spring-mvc.xml中配置
    public User save10() throws IOException {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(20);
        return user;
    }

    //获取请求数据（基本类型参数）
    @RequestMapping(value = "/quick11")
    @ResponseBody//不进行页面跳转
    public void save11(String username,int age) throws IOException {//要求url中参数和形参完全一致，若不是需要使用@requestParm注解
        //http://localhost:8080/quick11?username=aaa&age=30
        System.out.println(username);
        System.out.println(age);
        //会在控制台打印url中的参数：aaa 30
    }

    //获取请求数据（POJO参数）
    @RequestMapping(value = "/quick12")
    @ResponseBody//不进行页面跳转
    public void save12(User user) throws IOException {
        //http://localhost:8080/quick12?username=aaa&age=30
        System.out.println(user);
        //会在控制台打印url中的参数构成的User对象：User{username='aaa', age=30}
    }
    //获取请求数据（数组类型参数）
    @RequestMapping(value = "/quick13")
    @ResponseBody//不进行页面跳转
    public void save13(String[] strs) throws IOException {
        //http://localhost:8080/quick13?strs=aaa&strs=bbb&strs=ccc
        System.out.println(Arrays.asList(strs));
        //[aaa, bbb, ccc]
    }

    //获取请求数据（集合类型参数）
    //方式一
    @RequestMapping(value = "/quick14")
    @ResponseBody//不进行页面跳转
    public void save14(VO vo) throws IOException {
        //Post方式
        //http://localhost:8080/form.jsp
        System.out.println(vo);
        //VO{userList=[User{username='zhansan', age=11}, User{username='lisi', age=21}]}
        //中文乱码需要在web.xml中配置过滤器
    }
    //获取请求数据（集合类型参数）
    //方式二：ajax请求
    @RequestMapping(value = "/quick15")
    @ResponseBody//不进行页面跳转
    public void save15(@RequestBody List<User> userList) throws IOException {
        //http://localhost:8080/quick15
        System.out.println(userList);
    }


    //@requestParam参数绑定注解
    @RequestMapping(value = "/quick16")
    @ResponseBody//不进行页面跳转
    //要求url中参数和形参完全一致，若不是需要使用@requestParm注解
    public void save16(@RequestParam("name") String username) throws IOException {
        //http://localhost:8080/quick16?name=zhangsan
        System.out.println(username);
    }
    //Restful风格实例
    @RequestMapping(value = "/quick17/{name}")
    @ResponseBody//不进行页面跳转
    //GET
    //PathVariable的value值必须和RequestMapping的{}中的值相同
    public void save17(@PathVariable(value = "name",required = true) String username) throws IOException {
        //http://localhost:8080/quick17/zhangsan
        System.out.println(username);
    }

    //自定义日期类型转换器
    @RequestMapping(value = "/quick18")
    @ResponseBody//不进行页面跳转
    public void save18(Date date) throws IOException {
        //http://localhost:8080/quick18?date=2021-5-16没有自定义日期转换器会报错
        System.out.println(date);
    }

    @RequestMapping("/quick19")
    @ResponseBody
    public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }
    //获取请求头：User-Agent
    @RequestMapping("/quick20")
    @ResponseBody
    public void save20(@RequestHeader(value="User-Agent",required = false) String user_agent){
        System.out.println(user_agent);
    }
    //获取请求头：cookie
    @RequestMapping("/quick21")
    @ResponseBody
    public void save21(@CookieValue(value="JSESSIONID",required = false) String jsessionId){
        System.out.println(jsessionId);
    }
    //文件上传：增加形参
    @RequestMapping("/quick22")
    @ResponseBody
    //post upload.jsp form
    public void save22(String username, MultipartFile uploadFile,MultipartFile uploadFile2) throws IOException {//名称和form表单保持一致
        System.out.println(username);
        //System.out.println(uploadFile);
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("C:\\Users\\Jay\\Desktop\\1"+originalFilename));//避免相同位置重名
        String originalFilename2 = uploadFile2.getOriginalFilename();
        uploadFile2.transferTo(new File("C:\\Users\\Jay\\Desktop\\2"+originalFilename2));//避免相同位置重名
    }
    //文件上传：数组
    @RequestMapping("/quick23")
    @ResponseBody
    //post uploadMulti.jsp form
    public void save23(String username, MultipartFile[] uploadFiles) throws IOException {//名称和form表单保持一致
        System.out.println(username);
        for (MultipartFile uploadFile:uploadFiles){
            String originalFilename = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File("C:\\Users\\Jay\\Desktop\\"+originalFilename));
        }
    }

}
