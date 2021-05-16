## Spring简介

### Spring是什么

Spring是分层的Java SE/EE应用full-stack轻量级开源框架，以IoC(Inverse Of Control：反转控制)和AOP（Aspect Oriented Programming:面向切面编程）为内核。

提供了**展现层 Springmvc**和**持久层Spring Jdbctemplate**以及**业务层事务管理**等众多的企业级应用支术
还能整合开源世界众多著名的第三方框架和类库，逐新成为使用最多的 Java EE企业应用开源框架。

### Spring的优势

**1)方便解耦，简化开发**
通过 Spring提供的loC容器，可以将对象间的依赖关系交由 Spring进行控制，避免硬编码所造成的过度耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。

**2)AOP编程的支持**
通过 Springe的AOP功能，方便进行面向切面编程，许多不容易用传统OOP实现的功能可以通过AOP轻松实现。
**3)声明式事务的支持**
可以将我们从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活的进行事务管理，提高发效率和质量。

**4)方便程序的测试**
可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。

**5)方便集成各种优秀框架**
Spring对各种优秀框架(Struts、 Hibemate、 Hessian、 Quartz等)的支持。

**6)降低 Javaee APII的使用难度**
Spring对 JAVA EE API(如JDBC、Javamail、远程调用等)进行了薄薄的封装层，使这些API的使用难度大为降低

**7)Java源码是经典学习范例**
Spring的源代码设计精妙、结构清晰、匠心独用，处处体现着大师对ava设计模式灵活运用以及对Java技术的高深造诣。它的源代码是Java技术的最佳实践的范例。

### Spring的体系结构

![image-20210507165633173](https://gitee.com/onenovice/pic-of-bed/raw/master/image/Spring%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84.png)

## Spring快速入门

![image-20210507170121085](https://gitee.com/onenovice/pic-of-bed/raw/master/image/Spring%E5%BC%80%E5%8F%91%E6%AD%A5%E9%AA%A4.png)

①导入 Spring开发的基本包坐标
②编写Dao接口和实现类
③创建Spring核心配置文件
④在Spring配置文件中配置 Userdaolmpl
⑤使用Spring的API获得Bean实例

## Spring配置文件

### 1.Bean标签基本配置

用于配置对象交由Spring来创建
默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功

基本属性
`id`:Bean实例在 Spring容器中的唯一标识
`class`:Bean的全限定名称

### 2.Bean标签范围配置

scope：指对象的作用范围

| 取值范围         | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| **`singleton`**  | 默认的，单例的                                               |
| **`prototype`**  | 多例的                                                       |
| `request`        | WEB项目中， Spring创建一个Bean的对象，将对象存入到 request域中 |
| `session`        | WEB项目中， Spring创建一个Bean的对象，将对象存入到 session域中 |
| `global session` | WEB项目中，应用在Portlet环境，如果没有Portlet环境那么 global Session相当于session |

**1)当 scope的取值为 `singleton`时**
Bean的实例化个数：1个
Bean的实例化时机：**当 Spring核心文件被加载时，实例化配置的Bean实例**
Bean的生命周期：

- 对象创建：当应用加载，创建容器时，对象就被创建了
- 对象运行：只要容器在，对象一直活着
- 对象销毁：当应用卸载，销毁容器时，对象就被销毁了

**2)当 scope的取值为 prototype时**
Bean的实例化个数：多个
Bean的实例化时机：**当调用 getbean()方法时实例化Bean**

- 对象创建：当使用对象时，创建新的对象实例
- 对象运行：只要对象在使用中，就一直活着
- 对象销毁：当对象长时间不用时，被Java的垃圾回收器回收了

### 3.Bean生命周期配置

- `init-method`:指定类中的初始化方法名称
- `destroy-method`:指定类中销毁方法名称

### 4.Bean实例化三种方式

- 无参**构造**方法实例化（重点）
- 工厂**静态**方法实例化
- 工厂**实例**方法实例化

### 5.Bean的依赖注入分析

![image-20210507194612881](https://gitee.com/onenovice/pic-of-bed/raw/master/image/Bean%E7%9A%84%E4%BE%9D%E8%B5%96%E6%B3%A8%E5%85%A5%E5%88%86%E6%9E%90.png)

转为

![依赖注入分析2](https://gitee.com/onenovice/pic-of-bed/raw/master/image/%E4%BE%9D%E8%B5%96%E6%B3%A8%E5%85%A5%E5%88%86%E6%9E%902.png)

### 6.Bean的依赖注入概念

依赖注入(Dependency Injection):它是Spring框架核心IOC的具体实现。

在编写程序时，通过控制反转，把对象的创建交给了 Spring,但是代码中不可能出现设有依赖的情况。`IOC`解耦只是降低他们的依赖关系，但不会消除。例如：业务层仍会调用持久层的方法

那这种业务层和持久层的依赖关系，在使用 Spring之后，就让 Spring来维护了。简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取



**怎么将 Userdao怎样注入到 Userservice内部呢**

- 构造方法
- set方法

**1)set方法注入**

```xml
<!-- 依赖注入-->
    <bean id="userService" class="com.service.impl.UserServiceImpl">
<!--这里的name指的是setUserDao方法的UserDao部分将首字母小写-->
        <property name="userDao" ref="userDao"></property>
    </bean>
```

P命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便，主要体现在配置文件中，如下
首先，需要引入P命名空间

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

其次，需要修改注入方式

```xml
<bean id="userService "class="com.service.impl.UserServiceImpl" p:userDao-ref="userdao"/>
```

**2)构造方法**

```xml
 <bean id="userService" class="com.service.impl.UserServiceImpl">
        <constructor-arg name="userDao" ref="userDao"></constructor-arg>
    </bean>
```

7.Bean的依赖注入的数据类型

上面的操作，都是注入的引用Bean,处了对象的引用可以注入，普通数据类型，集合等都可以在容器中进行注入。

注入数据的三种数据类型

- 普通数据类型
- 引用数据类型
- 集合数据类型

```xml
<bean id="userDao" class="com.dao.impl.UserDaoImpl" init-method="init" destroy-method="destroy">
<!--        普通属性注入
        <property name="username" value="张三"/>
        <property name="age" value="18"/>-->
        <!-- 集合注入-->
        <property name="strList">
           <list>
               <value>aaa</value>
               <value>bbb</value>
               <value>ccc</value>
           </list>
        </property>
        <property name="userMap">
            <map>
               <entry key="u1" value-ref="user1"></entry>
               <entry key="u2" value-ref="user2"></entry>
            </map>
        </property>
        <property name="properties">
            <props>
                <prop key="p1">ppp1</prop>
                <prop key="p2">ppp2</prop>
                <prop key="p3">ppp3</prop>
            </props>
        </property>
        <!-- 集合注入部分结束（包含下面user1和user2的bean部分） -->
    </bean>
    <bean id="user1" class="com.domain.User">
        <property name="name" value="Tom1"></property>
        <property name="addr" value="Beijing"></property>
    </bean>
    <bean id="user2" class="com.domain.User">
        <property name="name" value="Tom2"></property>
        <property name="addr" value="Beijing"></property>
    </bean>
```

### 7.引入其他配置文件(分模块开发)

实际开发中， Spring的配置内容非常多，这就导致 Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件（按照分层或实体等）中，而在Spring主配置文件通过 import标签进行加载

```xml
<import resource="application Context-xxx.xml"/>
```

### 8.总结

![Spring的重点配置](https://gitee.com/onenovice/pic-of-bed/raw/master/image/Spring%E7%9A%84%E9%87%8D%E7%82%B9%E9%85%8D%E7%BD%AE.png)

## Spring相关API

### 1.ApplicationContext的继承体系

applicationContext:接口类型，代表应用上下文，可以通过其实例获得Spring容器中的Bean对象

### 2.ApplicationContext的实现类

**1)`ClassPathXmlapplicationContext`**
它是从类的根路径下加载配置文件(**推荐使用这种**)

**2) `FileSystemXmlapplicationContext`**
它是从磁盘路径上加載配置文件，配置文件可以在磁盘的任意位置

**3)`AnnotationConfigApplicationContext`**
当使用注解配置容器对象时，需要使用此类来创建Spring容器。它用来读取注解

### 3.getBean()方法

```java
//根据id，允许类型相同多个对象
public Obiect getbean(String name)throws BeansException{
	assertBeanFactoryActive();
	return getBeanFctory().getBean(name);
}
//根据类型，不允许类型相同的多个对象
public<T> T getBean(Class<T> requlredType)throws BeansException {
    assertBeanFactoryActive();
    return getBeanFactory().getBean(requlredType);
}
```

## Spring配置数据源

### 1.数据源（连接池）的作用

- 数据源（连接池）是提高程序性能出现的
- 事先实例化数据源，初始化部分连接资源
- 使用连接资源时从数据源中获取
- 使用完毕后将连接资源归还给数据源

### 2.Spring配置数据源

**将DataSource的创建权交由Spring容器完成**

Druid为例（初）

```xml
<!--   applicationContext.xml     -->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/shop"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>
```

```java
//测试Spring容器产生数据源对象(Druid数据源为例)
    //见applicationContext.xml
    @Test
    public void test4() throws Exception {
        ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource druidDataSource = app.getBean(DruidDataSource.class);
        Connection conn=druidDataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }
```

**applicationContext.xml加载jdbc.properties配置文件获得连接信息（升级）**

首先，需要引入context命名空间和约束路径

- 命名空间

```xml
xmlns:context="http://wwwspringframework.org/schema/context"
```

- 约束路径

```xml
http://www.springframework.org/schema/context
http://www.springframework.org/schemacontext/spring-context.xsd
```

```xml
<context: property-placeholder location="classpath: jdbc properties"/>
<bean id=""data Source"class="com.mchange v2. c3p0. Combopooleddatasource">
property name="driverclass"value="S{jdbc driver}"/>
<property name="jdbcurl"value="S{idbc.url}"/>
<property name="user"value="S{jdbc.username}"/>
<property name="password"value="S{jdbc.password}"/>
</bean>
```

## Spring注解开发

### 1.Spring原始注解

Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率

Spring原始注解主要是替代`<Bean>`的配置

|      注解       |                      说明                      |
| :-------------: | :--------------------------------------------: |
|  `@Component`   |            使用在类上用于实例化Bean            |
|  `@Controller`  |         使用在web层类上用于实例化Bean          |
|   `@Service`    |         使用在service层用于实例化Bean          |
|  `@Repository`  |           使用咋dao层用于实例化Bean            |
|  `@Autowired`   |        使用在字段上用于根据类型依赖注入        |
|  `@Qualifier`   | 结合@Autowired一起使用用于根据名称进行依赖注入 |
|   `@Resource`   | 相当于@Autowired + @Qualifier,按照名称进行诸如 |
|    `@Value`     |                  注入普通属性                  |
|    `@Scope`     |                标注Bean作用范围                |
| `@PostContruct` |    使用在方法上标注该方法是Bean的初始化方法    |
|  `@PreDestroy`  |     使用在方法上标注该方法是Bean的销毁方法     |

### 2.Spring新注解

使用上面的注解还不能全部替代xml配置文件，还需要使用注解替代的配置如下:

- 非自定义的Bean的配置：`<bean>`
- 加载 properties文件的配置：`<context:property-placeholder>`
- 组件扫描的配置：`<context:component-scan>`

- import子配置文件

|      注解       |                             说明                             |
| :-------------: | :----------------------------------------------------------: |
| @Configuration  | 用于指定当前类是一个 Spring配置类，当创建容器时会从该类上加载注解 |
| @ComponentSacn  | 用于指定 Spring在初始化容器时要扫描的包。作用和在 Spring的xml配置文件中的`<context:component-scan base-package="com"/>`一样 |
|      @Bean      |     使用在方法上，标注将该方法的返回值存储到Spring容器中     |
| @PropertySource |              用于加载`.properties`文件中的配置               |
|     @Import     |                      用于导入其他配置类                      |

## Spring整合Junit

### 1.原始Junit测试Spring的问题

在测试类中，每个测试方法都有以下两行代码：

```java
Applicationcontext ac =new Classpathxmlapplicationcontext("bean.xml");
IAccountService as=ac.getbean("accountService",IAccountservice.class);
```

这两行代码的作用是获取容器，如果不写的话，直接会提示空指针异常。所以又不能轻易删掉。

### 2.解决思路

- 让SpringJunit负责创建Spring容器，但是需要将配置文件的名称告诉它
- 将需要进行测试Bean直接在测试类中进行注入

### 3.Spring集成Junit步骤

1. 导入Spring集成Junit的坐标
2. 使用`@Runwith`注解替换原来的运行期
3. 使用`@ContextConfiguration`指定配置文件或配置类
4. 使用`@Autowired`注入需要测试的对象
5. 创建测试方法进行测试

## Spring的AOP简介

### AOP概念

Aspect Oriented Programming的缩写，意思是**面向切面编程**，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

AOP是OOP的延续，是软件开发中的一个热点，也是 Spring框架中的一个重要内容，是函数式编程的一种衍生范型，利用AOP可以对业务指的各个部分进行隔离，从而使得业务逻指各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

### AOP的作用和优势

作用：在程序运行期间，在不修改源码的情况下对方法进行功能增强

优势：减少重复代码，提高开发效率，并且便于维护

### AOP底层实现

实际上，AOP的底层是通过Spring提供的的动态代理技术实现的。在运行期，Spring通过动态代理技术动态的生成代理对象，代理对象方法执行时进行増强功能的介入，在去调用目标对象的方法，从而完成功能的増强。

### AOP动态代理技术

常用的动态代理技术

- JDK代理：基于接口的动态代理技术
- cglib代理：基于父类的动态代理技术

![AOP动态代理](https://gitee.com/onenovice/pic-of-bed/raw/master/image/AOP%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86.png)

AOP相关概念

Spring的AOP实现底层就是对上面的动态代理的代码进行了封装，封装后我们只需要对需要关注的部分进行代码编写，并通过配置的方式完成指定目标的方法増强。
在正式讲解AOP的操作之前，我们必须理解AOP的相关术语，常用的术语如下：

- **Target**（目标对象）：代理的目标对象
- **Proxy**（代理）：一个类被AOP织入増强后，就产生一个结果代理类
- **Joinpoint**（连接点）：所谓连接点是指那些被拦截到的点。在Spring中这些点指的是方法，因为 Spring只支持方法类型的连接点（可以被增强的方法称为连接点）
- **`Pointcut`**（切入点）：切入点是指我们要对哪些Joinpoint进行拦截的定义（被增强的叫切入点）
- **`Advice`**(通知/增强)：所谓通知是指拦截到Joinpoint之后所要做的事情就是通知
- `Aspect`（切面）：是切入点和通知（引介）的结合
- `Weaving`（织入）：是指把増强应用到目标对象来创建新的代理对象的过程。Spring采用动态代理只入，而AspectJ采用编译期织入和类装载期织入

### AOP开发明确的事项

#### 1.需要编写的内容

- 编写核心业务代码（目标类的目标方法）
- 编写切面类，切面类中有通知（增强功能方法）
- 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合

#### 2.AOP技术实现内容

Spring框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行。

#### 3.AOP底层使用哪些代理方式

在 Spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式。

### 总结

- aop:面向切面编程
- aop底层实现：基于JDK的动态代理和基于 Cglib的动态代理
- aop的重点概念
  Pointcut（切入点）：被增强的方法
  Advice(通知/增强)：封装増强业务逻辑的方法
  Aspect（切面）：切点 + 通知
  Weaving（织入）：将切点与通知结合的过程
- 开发明确事项：
  谁是切点（切点表达式配置）
  谁是通知（切面类中的増强方法）
  将切点和通知进行织入配置

## Spring的XML的AOP开发

### 快速入门

1. 导入AOP相关坐标
2. 创建目标接口和目标类（内部有切点）
3. 创建切面类（内部有增强方法）
4. 将目标类和切面类的对象创建权交给Spring
5. 在`applicationContext.xml`中配置织入关系
6. 测试代码

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--  目标对象  -->
    <bean id="target" class="com.aop.Target"></bean>

    <!--切面对象        -->
    <bean id="myAspect" class="com.aop.MyAspect"></bean>

    <!--  组织编入 ：告诉Spring进行哪些方法（切点）需要进行哪些增强 -->
    <aop:config>
        <!--声明切面-->
        <aop:aspect ref="myAspect">
            <!--切面：切点 + 通知 -->
            <aop:before method="before" pointcut="execution(public void com.aop.Target.save())"/>
        </aop:aspect>

    </aop:config>
</beans>
```

### 2.XML配置AOP详解

#### 1.切点表达式的写法

表达式语法

```
execution([修饰符]返回值类型 包名.类名.方法名(参数)
```

- 访问修饰符可以省略
- **返回值类型、包名、类名、方法名可以使用星号*代表任意**
- 包名与类名之间一个点`.`代表当前包下的类，两个点`..`表示当前包及其子包下的类
- 参数列表可以使用两个点`..`表示任意个数，任意类型的参数列表

示例

```
execution(public void com.itheima.aop.Target.method())
execution(void com.thelma.aop.Target.*(..))
execution(* com.ltheima.aop.**(..))
execution(* com.theima.aop..*.*(..))
execution(* *..*.*(..))
```

#### 2.通知的类型

通知的配置语法

```xml
<aop:通知类型 method="切面类中方法名" pointcut="切点表达式"></aop:通知类型>
```

|     名称     |          标签           |                             说明                             |
| :----------: | :---------------------: | :----------------------------------------------------------: |
|   前置通知   |     `<aop:before>`      |     用于配置前置通知。指定增强的方法在切入点方法之前执行     |
|   后置通知   | `<aop:after-returning>` |     用于配置后置通知。指定增强的方法在切入点方法之后执行     |
|   环绕通知   |     `<aop:around>`      | 用于配置环绕通知。指定増强的方法在切入点方法之前和之后都执行 |
| 异常抛出通知 |    `<aop:throwing>`     |     用于配置异常抛出通知。指定增强的方法在出现异常时执行     |
|   最终通知   |      `<aop:after>`      |     用于配置最终通知。无论增强方式执行是否有异常都会执行     |

#### 3.切点表达式的抽取

当多个增强的切点表达式相同时，可以将切点表达式进行抽取，在增强中使用`pointcut-ref`属性代替`pointcut`属性来引用抽取后的切点表达式。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--  目标对象  -->
    <bean id="target" class="com.aop.Target"></bean>

    <!--切面对象        -->
    <bean id="myAspect" class="com.aop.MyAspect"></bean>

    <!--  组织编入 ：告诉Spring进行哪些方法（切点）需要进行哪些增强 -->
    <aop:config>
      <!-- 抽取切点表达式-->
        <aop:aspect ref="myAspect">
            <aop:pointcut id="myPointcut" expression="execution(* com.aop.*.*(..))"></aop:pointcut>
            <aop:around method="around" pointcut-ref="myPointcut"></aop:around>
            <aop:after-throwing method="afterThrowing" pointcut-ref="myPointcut"></aop:after-throwing>
            <aop:after method="after" pointcut-ref="myPointcut"></aop:after>

        </aop:aspect>

    </aop:config>
</beans>
```



## 基于注解的AOP开发

### 1.快速入门

基于注解的aop开发步骤：
①创建目标接口和目标类（内部有切点）
②创建切面类（内部有增强方法）
③将目标类和切面类的对象创建权交给Spring
④在切面类中使用注解配置织入关系
⑤在配置文件中开启组件扫描和AOP的自动代理
⑥测试

### 2.注解配置AOP详解

通知的配置语法：`@通知注解("切点表达式")`

|     名称     |       标签        |                             说明                             |
| :----------: | :---------------: | :----------------------------------------------------------: |
|   前置通知   |     `@Before`     |     用于配置前置通知。指定增强的方法在切入点方法之前执行     |
|   后置通知   | `@AfterReturning` |     用于配置后置通知。指定增强的方法在切入点方法之后执行     |
|   环绕通知   |     `@Around`     | 用于配置环绕通知。指定増强的方法在切入点方法之前和之后都执行 |
| 异常抛出通知 | `@AfterThrowing`  |     用于配置异常抛出通知。指定增强的方法在出现异常时执行     |
|   最终通知   |     `@After`      |     用于配置最终通知。无论增强方式执行是否有异常都会执行     |

### 3.切点表达式的抽取

同xml配置aop一样，我们可以将切点表达式抽取。抽取方式是在切面内定义方法，在该方法上使用`@Pointcut`注解定义切点表达式。然后在在増强注解中进行引用。具体如下

## Spring JdbcTemplate的基本使用

### 概述

它是Spring框架中提供的一个对象，是对原始繁琐的Jdbc API对象的简单封装。 Spring框架为我们提供了很多的操作模板类。例如：操作关系型数据的 JdbcTemplate和HibernateTemplate,操作nosql据库的RedisTemplate,操作消息队列的JmsTemplates等等。

### JdbcTemplate开发步骤

①导入spring-jdbc和 spring-tx坐标
②创建数据库表和实体
③创建JdbcTemplate对象
④执行数据库操作

### Spring产生JdbcTemplate对象

我们可以将JdbcTemplatef的创建权交给Spring,将数据源Datasource的创建权也交给Spring,在Spring容器内部将数据源Datasource注入到JdbcTemplate模版对象中，配置如下：

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
">
        <!--该页的配置须掌握！！-->
<!--加载jdbc.properties-->
    <context:property-placeholder location="jdbc.properties"/>
<!--        数据源对象-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
<!--    jdbc模板对象-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```

jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/shop
jdbc.username=root
jdbc.password=123456
```

测试

```java
/**
增删改查测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    //修改
    public void updateTest(){
        jdbcTemplate.update("update account set money=? where name=?",10000,"Tom");
    }
    @Test
    //删除
    public void deleteTest(){
        jdbcTemplate.update("delete from account where name=?","Eric");
    }

    @Test
    //查询所有
    public void queryAllTest(){
        List<Account> query = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(query);
    }

    @Test
    //查询一行
    public void queryOneTest(){
        Account query = jdbcTemplate.queryForObject("select * from account where name=?",new BeanPropertyRowMapper<Account>(Account.class),"Tom");
        System.out.println(query);
    }

    @Test
    //查询总行数
    public void queryCountTest(){
        Long count = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        System.out.println(count);
    }

}
```

### 总结

①导入spring-jdbc和 spring-tx坐标
②创建数据库表和实体
③创建JdbcTemplate对象

```java
JdbcTemplate jdbcTemplate = new Jdbctemplate();
jdbcTemplate.setDataSource(datasource);
```

④执行数据库操作
更新操作：

```
Jdbctemplate.update(sql,params)
```

查询操作

```
jdbcTemplate.query(sql,Mapper,params)
jdbcTemplate.queryForObject(sql,Mapper,params)
```

## 编程式事务控制相关对象（了解）

### 1.PlatformTransactionManager

该接口是Spring的事务管理器，它里面提供了我们常用的操作事务的方法。

- TransactionStatus getTransactlon(Transaction Defination defination):获取事务状态信息
- void commit(TransactionStatus status)：提交事务
- void rollback(Transactionstatus status)：回滚事务

PlatformTransactionManager是接口类型，不同的Dao层技术则有不同的实现类

### 2.TransactionDefination

Transaction Definition是事务的定义信息对象，里面有如下方法：

|             方法             |        说明        |
| :--------------------------: | :----------------: |
|   int getIsolationLevel()    | 获得事务的隔离级别 |
| int getPropogationBehavior() | 获得事务的传播行为 |
|       int getTimeout()       |    获得超时时间    |
|     boolean isReadOnly()     |      是否只读      |

### 3.TransactionStatus

Transactionstatus接口提供的是事务具体的运行状态，方法介绍如下

|            方法            |      说明      |
| :------------------------: | :------------: |
|   boolean hasSavepoint()   | 是否存储回滚点 |
|   boolean isCompleted()    |  事务是否完成  |
| boolean isNewTransaction() |  是否是新事物  |
|  boolean isRollbackOnly()  |  事务是否回滚  |

## 基于XML的声明式事务控制

### 1.声明式事务控制

Spring的声明式事务就是**采用声明的方式来处理事务**。这里所说的声明，就是指在配置文件中声明，用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。

**声明式事务处理的作用**

- 事务管理不侵入开发的组件。具体来说，业务逻辅对象就不会意识到正在事务管理之中，事实上也应该如此，因为事务管理是属于系统层面的服务，而不是业务逻辑的部分，如果想要改变事务管理策划的话，也只需要在定义文件中重新配置即可。
- 在不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无需改变代码重新编译，这样维护起来极其方便。

> 注意：Spring声明式事务控制底层就是AOP。

### 2.声明式事务控制的实现

明确事项：

- 谁是切点
- 谁是通知
- 配置切面

示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
">

    <context:property-placeholder location="jdbc.properties"/>
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <bean id="accountDao" class="com.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>



<!--   目标对象 内部的方法就是切点-->
    <bean id="accountService" class="com.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

<!--    配置平台事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
<!--通知 事务的增强-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--设置事务的属性信息 -->
        <tx:attributes>
            <tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false"></tx:method>
<!--  下面表示任意方法均被增强，其他属性都默认-->
<!-- <tx:method name="*"/>-->
        </tx:attributes>
    </tx:advice>
<!--    配置事务的AOP织入-->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.service.impl.*.*(..))"></aop:advisor>
    </aop:config>

</beans>
```

### 3.切点方法的事务参数配置

示例见上

其中，`<tx:method>`代表切点的方法的事务参数的配置

属性中，

- `name`：切点方法名称（可使用通配符）
- `isolation`：事务的隔离级别
- `propogation`：事务的传播行为
- `timeout`：超时时间
- `read-only`：是否只读

## 基于注解的声明式事务控制

### 1.示例

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
">
    <context:property-placeholder location="jdbc.properties"/>
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

<!--    组件扫描-->
    <context:component-scan base-package="com"/>

<!--    自定义的类使用注解-->

<!--    配置平台事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--通知 事务的增强，使用注解配置时必须加下面语句(事务的注解驱动)-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>
```

AccountServiceImpl.java

```java
@Service("accountService")
//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
//写在类前代表适用类中所有方法，但若具体方法也使用了该注解则以方法前的为准
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void transfer(String outMan,String inMan,double money){

        accountDao.out(outMan,money);
        //这期间有异常会导致一个人前减少，而另一个没增加
        //制造 除0 错误检验
        
        //int i=1/0;//取消注释比较
        accountDao.in(inMan, money);
    }
}
```

### 2.注解配置声明式事务控制解析

①使用`@Transactional`在需要进行事务控制的类或是方法上修饰，注解可用的属性同xml配置方式，例如隔离级别、传播行为等。

②注解使用在类上，那么该类下的所有方法都使用同套注解参数配置

③使用在方法上，不同的方法可以采用不同的事务参数配置
④xml配置文件中要开启事务的注解驱动`<tx:annotation-driven/>`

## Spring集成web环境

### 1.ApplicationContext应用上下文获取方式

应用上下文对象是通过 `new ClasspathXmlApplicationContext(springe配置文件)`方式获取的，但是每次从容器中获得Bean时都要编写new ClasspathXmlApplicationContext(spring配置文件)，这样的弊端是配置文件加载多次，应用上下文对象创建多次。

在Web项目中，可以使用ServletContextListener监听Web应用的启动，我们可以在Web应用启动时，就加载Spring的配置文件，创建应用上下文对象ApplicationContext,在将其存储到最大的域 servletContext域中，这样就可以在任意位置从域中获得应用上下文ApplicationContext对象了。

### 2.Spring提供获取应用上下文的工具

**上面的分析不用手动实现**，Spring提供了一个监听器ContextLoaderListener就是对上述功能的封装，该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到ServletContext域中，提供了一个客户端工具WebApplicationContextUtils供使用者获得应用上下文对象。

我们只需要：

①在`web.xml`中配置`ContextLoaderListener`监听器(导入`spring-web`坐标)

②使用`WebApplicationContextUtils`获得应用上下文对象`ApplicationContext`

## Spring MVC

### 1.概述

Spring MVC是一种基于Java的实现MVC设计模型的请求驱动类型的轻量级Web框架，属于
SpringFramework的后续产品，已经融合在Spring Web Flow中。

`Spring MVC`已经成为目前最主流的MVC框架之一，并目**随着Spring3.0**的发布，全面超越Struts2,成为**最优秀的MVC框架**。它通过一套注解，让一个简单的Java类成为处理请求的控制器，而无须实现任何接口。同时，它还支持`RESTful`编程风格的请求

![springMVC概述](https://gitee.com/onenovice/pic-of-bed/raw/master/image/SpringMVC%E6%A6%82%E8%BF%B0.png)


### 2.SpringMVC快速入门
![SpringMVC开发步骤](https://gitee.com/onenovice/pic-of-bed/raw/master/image/SpringMVC%E5%BC%80%E5%8F%91%E6%AD%A5%E9%AA%A4.png)

需求：客户端发起请求，服务器端接收请求，执行逻辑并进行视图跳转

**开发步骤**
①导入SpringMVC相关坐标
②配置SpringMVC核心控制器DispathcerServlet
③创建Controller类和视图页面
④使用注解配置Controller类中业务方法的映射地址
⑤配置SpringMVC核心文件spring-mvc. xml
⑥客户端发起请求测试



SpringMVC流程图

![流程图Springmvc](https://gitee.com/onenovice/pic-of-bed/raw/master/image/SpringMVC%E6%B5%81%E7%A8%8B%E5%9B%BE.png)

### 3.SpringMVC组件解析

#### 1.SpringMVC执行流程（理解）

![SpringMVC执行图示](https://gitee.com/onenovice/pic-of-bed/raw/master/image/SpringMVC%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B%E5%9B%BE%E7%A4%BA%E8%AF%A6%E8%A7%A3.png)

①用户发送请求至前端控制器DispatcherServlet.
② DispatcherServlet收到请求调用HandlerMapping处理器映射器。
③处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器（如果有则生成）一并返回给Dispatcherservlet。
④ Dispatcherservlet调用Handleradapter处理器适配器。
⑤ HandlerAdapter经过适配调用具体的处理器(Controller,也叫后端控制器)。
⑥ Controllers执行完成返回ModelAndView。
⑦ HandlerAdapter将controller执行结果ModelAndView返回给Dispatcherservlet。
⑧ DispatcherServlet将Modelandview传给ViewReslover视图解析器。
⑨ ViewResloverf解析后返回具体View.
⑩ DispatcherServlet根据View进行渲染视图（将模型数据填充至视图中）。 DispatcherServlet响应用户。

#### 2.SpringMVC注解解析

`@Requestmapping`

作用：用于建立请求URL和处理请求方法之间的对应关系



位置
**类**上：请求URL的第一级访问目录。此处不写的话，就相当于应用的根目录
**方法**上：请求URL的第二级访可目录，与类上的使用`@Reqquestmapping`标主的级目录起组成询问虚拟路径



属性

`value`:用于指定请求的URL。它和path属性的作用是一样的。
`method`:用于指定请求方式。
`params`:用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和 value必须和配置的一模一样。

	例如
	params={"accountName"},表示请求参数必须有accountName
	params={"moeny!100"),表示请求参数中money不能是100
#### 3.SpringMVC的XML配置解析

1. **mvc命名空间的引入**

命名空间

```xml
xmlns:context="http://www.springframework.org/schema/context"
xmlns:mvc="http://www.springframework.org/schema/mvc"
```

约束地址

```xml
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
```

2. **组件扫描**

Springmvc基于Spring容器，所以在进行springmvc操作时，需要将 Controller存储到Spring容器中，如果使用 controller注解标注的话，就需要使用`<context:component-scan base
package=com.controller"/>`
进行组件扫描。

3. **视图解析器**

Springmvc有默认组件配置，默认组件都是DispatcherServ1et.properties配置文件中配置的，该配置文件地址org/springframework/web/sexvlet/ DispatcherServlet.properties,该文件中配置了默认的视图解析器，如下
`org.springframework.web.servlet.viewResolver=org.springframework.web.servletView.InternalResourceViewResolver`
翻看该解析器源码，可以看到该解析器的默认设置，如下：

```java
REDIRECT_URL_PREFIX="redirect:";  //重定向前缀
FORWARD_URL_PREFIX="forward:";    //转发前（默认值）
prefix="";          //视图名称前缀
suffix="";          //视图名称后缀
```

### 4.SpringMVC的数据响应

#### 1.页面跳转

- 直接返回字符串：此种方式会将返回的字符串与视图解析器的前后缀拼接后跳转。
- 通过ModelAndView对象返回

返回字符串形式实例

![image-20210513170316433](https://gitee.com/onenovice/pic-of-bed/raw/master/image/%E7%9B%B4%E6%8E%A5%E8%BF%94%E5%9B%9E%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%BD%A2%E5%BC%8F.png)

返回带有前缀的字符串
转发： `forward:/WEB-INF/views/index.jsp`
重定向：` redirect:/index.jsp`

```java
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
}
```



#### 2.回写数据

- **直接返回字符串**：Web基础阶段，客户端访问服务器端，如果想直接回写字符串作为响应体返回的话，只需要使用`response.getwriter().print("hello world")`即可，那么在 Controller中想直接回写字符串该怎样呢？（两种方式）

  - 通过SpringMVC框架注入的response对象，使用response.getwriter().print("hello world")回写数据，此时不需要视图跳转，业务方法返回值为void
  - 将需要回写的字符串直接返回，但此时需要通过`@Responsebody`注解告知SpringMVC框架，方法返回的字符串不是跳转视图是直接在http响应体中返回

  ```java
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
  ```

  

- **返回对象或集合**

在方法上添加`@Responsebody`就可以返可json格式的字符串，但是这样配置比较麻烦，配置的代码比较多，因此，我们可以使用mvc的注解驱动代替上述配置。

配置实例

spring-mvc.xml

```xml
<!--使得返回对象转换为json格式字符串 -->
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">
        <list>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
        </list>
    </property>
</bean>
```

```java
/*UserController.java*/
//返回对象或集合
@RequestMapping(value = "/quick10")
@ResponseBody
//期望SpringMVC自动将User对象转换成json格式字符串，需要在spring-mvc.xml中配置
public User save10() throws Exception {
    User user = new User();
    user.setUsername("lisi");
    user.setAge(20);
    return user;
}
```

使用mvc的注解驱动代替上述配置:

```xml
<mvc:annotation-driven/>
```

在Springmvc的各个组件中，**处理器映射器、处理器适配器、视图解析器**称为 Spring MVC的三大组件。使用`<mvc:annotation-driven>`自动加我RequestMappingHandlerMapping（处理映射器）和RequestMappingHandlerAdapter（处理适配器），可用在 Spring-xml配置文件中使用
`<mvc:annotation-driven>`替代注解处理器和适配器的配置。同时使用`<mvc:annotation-driven>`默认底层就会集成 jackson进行对象或集合的json格式字符申的转换。

### 5.SpringMVC获得请求数据

#### 1.获得请求参数

客户端请求参数的格式是：`name=value&name=value......`
服务器端要获得请求的参数，有时还需要进行数据的封装，SpringMVC可以接收如下类型的参数：

- 基本数据类型
- POJO类型参数
- 数组类型参数
- 集合类型参数

#### 2.获得基本类型参数

Controller中的业务方法的参数名称要与请求参数的name一致，参数值会自动映射匹配

```java
//获取请求数据（基本类型参数）
@RequestMapping(value = "/quick11")
@ResponseBody//不进行页面跳转
public void save11(String username,int age) throws IOException {
    //http://localhost:8080/quick11?username=aaa&age=30
    System.out.println(username);
    System.out.println(age);
    //会在控制台打印url中的参数：aaa 30
}
```

#### 3.获取POJO类型参数

Controller中的业务方法的POJO参数的属性名与请求参数的name一致，参数值会自动映射匹配

```java
//获取请求数据（POJO类型参数）
@RequestMapping(value = "/quick12")
@ResponseBody//不进行页面跳转
public void save12(User user) throws IOException {
    //http://localhost:8080/quick12?username=aaa&age=30
    System.out.println(user);
    //会在控制台打印url中的参数构成的User对象：User{username='aaa', age=30}
}
```

#### 4.获取数组类型参数

Controller中的业务方法数组名称与请求参数的name致，参数值会自动映射匹配

```java
//获取请求数据（数组类型参数）
@RequestMapping(value = "/quick13")
@ResponseBody//不进行页面跳转
public void save13(String[] strs) throws IOException {
    //http://localhost:8080/quick13?strs=aaa&strs=bbb&strs=ccc
    System.out.println(Arrays.asList(strs));
    //[aaa, bbb, ccc]
}
```

#### 5.获得集合类型参数

获得集合参数时，要将集合参数包装到个POJO中オ可以。

##### 方式一

```java
public class VO {
    private List<User> userList;

    @Override
    public String toString() {
        return "VO{" +
                "userList=" + userList +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
```



```java
 //获取请求数据（集合类型参数）
 @RequestMapping(value = "/quick14")
 @ResponseBody//不进行页面跳转
 public void save14(VO vo) throws IOException {
 //Post方式
 //http://localhost:8080/form.jsp
 System.out.println(vo);
 //VO{userList=[User{username='zhansan', age=11}, User{username='lisi', age=21}]}
 }
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取集合参数实例</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick14" method="post">
        <%--表明是第几个User对象的username和age--%>
        <input type="text" name="userList[0].username"><br>
        <input type="text" name="userList[0].age"><br>
        <input type="text" name="userList[1].username"><br>
        <input type="text" name="userList[1].age">
        <input type="submit" name="提交">
    </form>
</body>
</html>
```

##### 方式二

当使用ajax提交时，可以指定contentType为json形式，那么在方法参数位置使用`@Requestbody`可以
直接接收集合数据而无需使用POJO进行包装。

```java
//获取请求数据（集合类型参数）
//方式二：ajax请求
@RequestMapping(value = "/quick15")
@ResponseBody//不进行页面跳转
public void save15(@RequestBody List<User> userList) throws IOException {
//http://localhost:8080/quick15
System.out.println(userList);
}
```

ajax.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
<%--    涉及静态资源访问权限：在spring-mvc.xml中加下面语句
       <mvc:resources mapping="/js/**" location="/js/"/>
           或者
      <mvc:default-servlet-handler/>      
    --%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.js"></script>
    <script>
        var userList = new Array();
        userList.push({username:"zhangsan",age:18});
        userList.push({username:"lisi",age:20});

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/quick15",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        })
    </script>
</head>
<body>
</body>
</html>
```

#### 6.请求数据乱码问题

使用tomcat8的POST请求时，若含中文会出现乱码

在web.xml中配置过滤器

```xml
<!--配置全局过滤器filter,解决tomcat8 POST中文乱码问题-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### 7.参数绑定注解@requestParam

```java
//@requestParam参数绑定注解
@RequestMapping(value = "/quick16")
@ResponseBody//不进行页面跳转
//要求url中参数和形参完全一致，若不是需要使用@requestParm注解
public void save16(@RequestParam("name") String username) throws IOException {
//http://localhost:8080/quick16?name=zhangsan
System.out.println(username);
}
```

- `value`:与请求参数名称。
- `required`:此在指定的请求参数是否必须包括，默认是true,提交时如果没有此参数报错。
- `defaultvalue`:当没有指定请求参数时，则使用指定的默认值赋值

#### 8.获得`Restful`风格的参数

`Restful`是一种软件架构风格、设计风格，而**不是标准**，只是提供了一组设计原则和约束条件。主要用于客户端和服务器交互类的软件，基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存机制等。

`Restful`风格的请求是使用`url+请求方式`表示一次请求目的的，HTTP协议里面四个表示操作方式的动词如下：

- GET：用于获取资源
- POST：用于新建资源
- PUT：用于更新资源
- DELETE：用于删除资源

> 例如：
>
> - /user/1    GET：      得到id=1的user
> - /user/1    DELETE：删除id=1的user
> - /user/1    PUT ：     更新id=1的user
> - / user      POST:       新增user

上述url地址`/user/1`中的1就是要获得的请求参数，在SpringMVC中可以使用占位符进行参数绑定。地址`/user/1`可以写成`/user/{id}`,占位符id对应的就是1的值。在业务方法中我们可以使用`@PathVariable`注解进行占位符的匹配获取工作。

![PathVariable注解](https://gitee.com/onenovice/pic-of-bed/raw/master/image/pathVariable%E6%B3%A8%E8%A7%A3.png)

```java
//Restful风格实例
@RequestMapping(value = "/quick17/{name}")
@ResponseBody//不进行页面跳转
//GET
//PathVariable的value值必须和RequestMapping的{}中的值相同
public void save17(@PathVariable(value = "name",required = true) String username) throws IOException {
//http://localhost:8080/quick17/zhangsan
System.out.println(username);
}
```

#### 9.自定义类型转换器

- SpringMVC默认已经提供了一些常用的类型转换器，例如客户端提交的字符串转换成int型进行参数设置
- 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据就需要自定义转换器。



自定义类型转换器的开发步骤
①定义转换器类实现Converter接口
②在配置文件（spring-mvc.xml）中声明转换器
③在`<annotation-driven>`中引用转换器

```java
//自定义日期类型转换器
    @RequestMapping(value = "/quick18")
    @ResponseBody//不进行页面跳转
    public void save18(Date date) throws IOException {
        //http://localhost:8080/quick18?date=2021-5-16没有自定义日期转换器会报错
        System.out.println(date);
    }
```



```java
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String DateStr) {
        //将日期字符串转换成日期对象，返回
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = format.parse(DateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
```

```xml
<!--    声明转换器-->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <property name="converters">
        <list>
        <bean class="com.converter.DateConverter"></bean>
        </list>
    </property>
</bean>

<!--mvc注解驱动（以后此文件先写此驱动放置忘记）-->
    <!-- conversion-service属性和日期转换器id绑定 ,否则不写  -->
    <mvc:annotation-driven conversion-service="conversionService"/>
    
```

#### 10.获取响应、请求、session

```java
@RequestMapping("/quick19")
@ResponseBody
public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session){
    System.out.println(request);
    System.out.println(response);
    System.out.println(session);
}
```



#### 11.获取请求头

##### `@RequestHeader`

使用`@RequestHeader`以获得请求头信息，相当于web阶段学习的`request.getHeader(name)`
`@RequestHeader`注解的属性如下：

- `value`：请求头的名称
- `required`：是否必须携带此请求头

```java
//获取请求头：User-Agent
@RequestMapping("/quick20")
@ResponseBody
public void save20(@RequestHeader(value="User-Agent",required = false) String user_agent){
	System.out.println(user_agent);
}
```

##### `@CookieValue`

使用`@Cookievalue`可以获得指定Cookie的值
`@Cookievalue`注解的属性如下：

- `value`：指定cookie的名称
- `required`：是否必须携带此cookie

```java
//获取请求头：cookie
@RequestMapping("/quick21")
@ResponseBody
public void save21(@CookieValue(value="JSESSIONID",required = false) String jsessionId){
    System.out.println(jsessionId);
}
```

#### 12.文件上传

##### 1.文件上传客户端三要素

- 表单项`type="file"`
- 表单的提交方式是`post`
- 表单的`enctype`属性是多部分表单形式，及`enctype="multipart/form-data`

##### 2.原理

- 当form表单修改为多部分表单时，`request.getparameter()`将失效

- `enctype="application/x-www-form-urlencoded"`时，form表单的正文内容格式是
  `key1=value1&key2=value2&key3=value3`

- 当form表单的`enctype`取值为`Multipart/form-data`时，请求正文内容就变成多部分形式

  ![enctype:mutilpart](https://gitee.com/onenovice/pic-of-bed/raw/master/image/mutilpart-form-data.png)

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick22" method="post" enctype="multipart/form-data">
        名称<input type="text" name="username"/><br/>
        文件<input type="file" name="upload"/><br/>
        <input type="submit" name="提交">
    </form>
</body>
</html>
```

#### 13.单文件上传步骤

1. 导入 fileupload和io坐标

   ```xml
   <!--文件上传相关-->
   <dependency>
       <groupId>commons-io</groupId>
       <artifactId>commons-io</artifactId>
       <version>2.8.0</version>
   </dependency>
   <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>1.4</version>
   </dependency>
   ```

2. 配置文件上传解析器

   ```xml
   <!--文件上传配置-->
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <!--上传文件总大小-->
       <property name="maxUploadSize" value="5242800"/>
       <!--上传单个文件大小-->
       <property name="maxUploadSizePerFile" value="5242800"/>
       <!--上传文件的编码类型-->
       <property name="defaultEncoding" value="UTF-8"/>    
   </bean>
   ```

   

3. 编写文件上传代码

   ```java
   //文件上传：变更形参数量（配合form表单增加或减少形参即可）
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
   ```

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Upload</title>
   </head>
   <body>
       <form action="${pageContext.request.contextPath}/quick22" method="post" enctype="multipart/form-data">
           名称<input type="text" name="username"/><br/>
           文件<input type="file" name="uploadFile"/><br/>
           文件<input type="file" name="uploadFile2"/><br/>
           <input type="submit" name="提交">
       </form>
   </body>
   </html>
   ```

#### 14.多文件上传

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Jay
  Date: 2021/5/16
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/quick23" method="post" enctype="multipart/form-data">
    名称<input type="text" name="username"/><br/>
    文件1<input type="file" name="uploadFiles"/><br/>
    文件2<input type="file" name="uploadFiles"/><br/>
    文件3<input type="file" name="uploadFiles"/><br/>
    <input type="submit" name="提交">
</form>
</body>
</html>
```

```java
//文件上传：数组
@RequestMapping("/quick22")
@ResponseBody
//post uploadMulti.jsp form
public void save23(String username, MultipartFile[] uploadFiles) throws IOException {//名称和form表单保持一致
    System.out.println(username);
    for (MultipartFile uploadFile:uploadFiles){
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("C:\\Users\\Jay\\Desktop\\"+originalFilename));
    }
}
```

## SpringMVC拦截器

