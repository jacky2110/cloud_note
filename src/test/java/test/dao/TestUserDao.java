package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUserDao {
        
        private UserDao dao;
        
        @Before
        public void init() {
                // ����spring����
                ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mvc.xml");
                
                // ��ȡMapperӳ�������ӿڣ�
                dao = ctx.getBean("userDao", UserDao.class);
        }
        
        @Test
        public void testCase() {
                User user = dao.findByName("������");
                System.out.println(user);
                
                if (user != null) {
                        System.out.println("�û�����");
                } else {
                        System.out.println("�û�������");
                }
        }
        
        @Test
        public void testSave() {
                
                // ����һ��User������ģ��Ҫ����ļ�¼
                User user = new User();
                user.setCn_user_id("1234567980");
                user.setCn_user_name("������");
                user.setCn_user_password("123456");
                user.setCn_user_nick("����");
                
                dao.save(user);
        }
}
