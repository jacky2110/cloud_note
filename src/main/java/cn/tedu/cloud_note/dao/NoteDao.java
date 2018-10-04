package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
     // public List<Note> findByBookId(String bookId);
        // 像上面方法一样返回一个Note实体类的集合是完全可以的。但这个Note实体类中的大部分成员变量存储的信息我们都用不到。
        // 所以这里尝试使用Map类型作为返回值，只存储需要用到的字段即可
        public List<Map> findByBookId(String bookId);
        
        // 声明一个方法，用于根据笔记id查询笔记内容
        public Note findByNoteId(String noteId);
        
        // 声明一个方法，用于保存笔记内容
        public int updateNote(Note note);
        
        // 声明一个方法，用于创建新的笔记
        public int addNote(Note note);
}
