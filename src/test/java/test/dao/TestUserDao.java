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
                // 启动spring容器
                ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mvc.xml");
                
                // 获取Mapper映射器（接口）
                dao = ctx.getBean("userDao", UserDao.class);
        }
        
        @Test
        public void testCase() {
                User user = dao.findByName("张三丰");
                System.out.println(user);
                
                if (user != null) {
                        System.out.println("用户存在");
                } else {
                        System.out.println("用户不存在");
                }
        }
        
        @Test
        public void testSave() {
                
                // 构造一个User对象来模拟要插入的记录
                User user = new User();
                user.setCn_user_id("1234567980");
                user.setCn_user_name("张三丰");
                user.setCn_user_password("123456");
                user.setCn_user_nick("君宝");
                
                dao.save(user);
        }
}
