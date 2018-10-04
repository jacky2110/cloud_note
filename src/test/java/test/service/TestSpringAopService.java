package test.service;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.service.SpringAopService;
import test.TestBase;

public class TestSpringAopService extends TestBase {
        private SpringAopService sas;
        
        @Before
        public void init() {
                // ���ø���ķ�����ȡ������ʵ����Ȼ���ȡMapperӳ������beanʵ��
                sas = super.getContext().getBean("id_SpringAopServiceImpl", SpringAopService.class);
        }
        
        @Test
        public void testNormal() {
                System.out.println("TestSpringAopService.testNormal()=============");
                
                // ���׳��쳣
                sas.testAdvice(false);
        }
        
        @Test
        public void testException() {
                System.out.println("TestSpringAopService.testException()=============");
                
                // �׳��쳣
                sas.testAdvice(true);
        }
}
