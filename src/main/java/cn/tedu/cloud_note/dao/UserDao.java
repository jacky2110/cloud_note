package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
        // ���巽�������ڲ��Ҽ�¼
        public User findByName(String name);
        
        // ���巽����������Ӽ�¼
        public void save(User user);
}
