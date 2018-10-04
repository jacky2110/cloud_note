package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class TestBase {
        public ApplicationContext getContext() {
                // ����ж�������ļ������
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml", "conf/spring-aop.xml"};
                
                // ����spring����
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                return ctx;
        }
}
