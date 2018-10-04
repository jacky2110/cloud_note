package cn.tedu.cloud_note.service;

import org.springframework.stereotype.Service;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//例如这里是业务层的实现类，因此应该使用@Service   
@Service("id_SpringAopServiceImpl")    // 如果不指定id，则默认的id为首字母小写的类名
public class SpringAopServiceImpl implements SpringAopService {

        public void testAdvice(boolean throwException) {
                System.out.println("SpringAopServiceImpl.testAdvice() start ============");
                
                if (throwException) {
                        System.out.println("即将抛出异常");
                        throw new IllegalArgumentException("测试异常的抛出");
                } else {
                        System.out.println("没有抛出异常");
                }
                
                System.out.println("SpringAopServiceImpl.testAdvice() end ============");                
        }
}
