package test.service;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.service.SpringAopService;
import test.TestBase;

public class TestSpringAopService extends TestBase {
        private SpringAopService sas;
        
        @Before
        public void init() {
                // 调用父类的方法获取上下文实例，然后获取Mapper映射器的bean实例
                sas = super.getContext().getBean("id_SpringAopServiceImpl", SpringAopService.class);
        }
        
        @Test
        public void testNormal() {
                System.out.println("TestSpringAopService.testNormal()=============");
                
                // 不抛出异常
                sas.testAdvice(false);
        }
        
        @Test
        public void testException() {
                System.out.println("TestSpringAopService.testException()=============");
                
                // 抛出异常
                sas.testAdvice(true);
        }
}
