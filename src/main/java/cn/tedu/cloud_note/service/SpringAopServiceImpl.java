package cn.tedu.cloud_note.service;

import org.springframework.stereotype.Service;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������ҵ����ʵ���࣬���Ӧ��ʹ��@Service   
@Service("id_SpringAopServiceImpl")    // �����ָ��id����Ĭ�ϵ�idΪ����ĸСд������
public class SpringAopServiceImpl implements SpringAopService {

        public void testAdvice(boolean throwException) {
                System.out.println("SpringAopServiceImpl.testAdvice() start ============");
                
                if (throwException) {
                        System.out.println("�����׳��쳣");
                        throw new IllegalArgumentException("�����쳣���׳�");
                } else {
                        System.out.println("û���׳��쳣");
                }
                
                System.out.println("SpringAopServiceImpl.testAdvice() end ============");                
        }
}
