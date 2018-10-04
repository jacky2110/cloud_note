package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;

public interface UserService {
        // 声明一个方法，用于比对用户输入的账号和密码是否正确
        public NoteResult<User> checkLogin(String name, String password);
        
        // 声明一个方法，用于向数据库中插入一条记录
        // 由于插入操作不返回具体类型，所以使用Object类型
        public NoteResult<Object> addUser(String name, String password, String nick);
}
