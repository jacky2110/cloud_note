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
                
                // 针对有多个配置文件的情况
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml"};
                
                // 启动spring容器
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                // 获取Mapper映射器（接口）
                service = ctx.getBean("id_UserServiceImpl", UserService.class);
        }
        
        // 用例1，预期结果：用户名不存在
        @Test
        public void test1() {
                NoteResult<User> result = service.checkLogin("Trump", "123456");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 1
                // 用户名不存在
                // null
        }
        
        // 用例2，预期结果：密码不正确
        @Test
        public void test2() {
                NoteResult<User> result = service.checkLogin("demo", "123456");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 2
                // 密码不正确
                // null
        }
        
        // 用例3，预期结果：账号密码都正确
        @Test
        public void test3() {
                NoteResult<User> result = service.checkLogin("demo", "c8837b23ff8aaa8a2dde915473ce0991");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                // 0
                // 登录成功
                // User [cn_user_id=48595f52-b22c-4485-9244-f4004255b972, cn_user_name=demo, cn_user_password=c8837b23ff8aaa8a2dde915473ce0991, cn_user_token=null, cn_user_nick=null]
        }
        
        // 用例3，预期结果：注册成功
        @Test
        public void test4() {
                NoteResult<Object> result = service.addUser("王多鱼", "123456", "西虹市首富");
                
                System.out.println("result.status: " + result.getStatus());
                System.out.println("result.msg: " + result.getMsg());
        }
}
