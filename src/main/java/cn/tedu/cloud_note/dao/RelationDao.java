package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;

public interface RelationDao {
        public User findUserAndBooks(String userId);
}
