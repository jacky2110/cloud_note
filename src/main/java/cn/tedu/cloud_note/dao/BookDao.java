package cn.tedu.cloud_note.dao;

import java.util.List;

import cn.tedu.cloud_note.entity.Book;

public interface BookDao {
        public List<Book> findByUserId(String userId);
        
        // 声明一个函数，用于创建笔记本 (INSERT)
        public int addBook(Book book);
}
