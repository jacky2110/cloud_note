package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
        // 定义方法，用于查找记录
        public User findByName(String name);
        
        // 定义方法，用于添加记录
        public void save(User user);
}
