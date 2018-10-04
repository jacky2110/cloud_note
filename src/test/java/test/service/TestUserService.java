package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
        
        private UserService service;
        
        @Before
        public void init() {
                
                // ����ж�������ļ������
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml"};
                
                // ����spring����
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                // ��ȡMapperӳ�������ӿڣ�
                service = ctx.getBean("id_UserServiceImpl", UserService.class);
        }
        
        // ����1��Ԥ�ڽ�����û���������
        @Test
        public void test1() {
                NoteResult<User> result = service.checkLogin("Trump", "123456");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 1
                // �û���������
                // null
        }
        
        // ����2��Ԥ�ڽ�������벻��ȷ
        @Test
        public void test2() {
                NoteResult<User> result = service.checkLogin("demo", "123456");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 2
                // ���벻��ȷ
                // null
        }
        
        // ����3��Ԥ�ڽ�����˺����붼��ȷ
        @Test
        public void test3() {
                NoteResult<User> result = service.checkLogin("demo", "c8837b23ff8aaa8a2dde915473ce0991");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 0
                // ��¼�ɹ�
                // User [cn_user_id=48595f52-b22c-4485-9244-f4004255b972, cn_user_name=demo, cn_user_password=c8837b23ff8aaa8a2dde915473ce0991, cn_user_token=null, cn_user_nick=null]
        }
        
        // ����3��Ԥ�ڽ����ע��ɹ�
        @Test
        public void test4() {
                NoteResult<Object> result = service.addUser("������", "123456", "�������׸�");
                
                System.out.println("result.status: " + result.getStatus());
                System.out.println("result.msg: " + result.getMsg());
        }
}
