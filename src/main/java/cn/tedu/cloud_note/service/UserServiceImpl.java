package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//例如这里是业务层的实现类，因此应该使用@Service   
@Service("id_UserServiceImpl")    // 如果不指定id，则默认的id为首字母小写的类名
public class UserServiceImpl implements UserService {

        // 使用注释进行依赖注入
        @Resource(name="userDao")
        private UserDao userDao;
        
        public NoteResult<User> checkLogin(String name, String password) {
                NoteResult<User> result = new NoteResult<User>();
                
                // 按参数name查询数据库
                User user = userDao.findByName(name);
                
                // 如果在数据库中查询不到该用户
                if (user == null) {
                        result.setStatus(1);  // 自定义的约定，非零表示失败
                        result.setMsg("用户名不存在");
                        return result;
                }
                
                // 能查询到用户后，对密码进行验证
                String md5Password = NoteUtil.md5(password);
                if (user.getCn_user_password().equals(md5Password) == false) {
                        result.setStatus(2);  // 自定义的约定，非零表示失败
                        result.setMsg("密码不正确");
                        return result;
                }
                
                // 跑到这里表示账号密码通过验证
                result.setStatus(0);  // 自定义的约定，零表示成功
                result.setMsg("登录成功");
                result.setData(user);
                return result;
        }

        public NoteResult<Object> addUser(String name, String password, String nick) {
                
                NoteResult<Object> result = new NoteResult<Object>();
                
                // 检测用户名是否被占用
                User userFindByname = userDao.findByName(name);
                if (userFindByname != null) {  // 如果能搜到结果，表示用户名已经被占用
                        result.setStatus(1);  // 设置状态为1，表示用户名已经被占用
                        result.setMsg("用户名已经被占用");
                        return result;
                }
                        
                // 如果搜不到结果，表示用户名没有被占用，则准备用户信息
                User user = new User();
                user.setCn_user_id(NoteUtil.createId());
                user.setCn_user_name(name);
                user.setCn_user_password(NoteUtil.md5(password));
                user.setCn_user_nick(nick);
                
                // 将用户信息存入数据库
                userDao.save(user);
                
                // 准备并返回结果
                result.setStatus(0);  // 设置状态为1，表示存储成功
                result.setMsg("注册成功");
                return result;
        }
}
