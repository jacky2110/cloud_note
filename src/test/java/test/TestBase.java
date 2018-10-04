package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class TestBase {
        public ApplicationContext getContext() {
                // 针对有多个配置文件的情况
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml", "conf/spring-aop.xml"};
                
                // 启动spring容器
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                return ctx;
        }
}
