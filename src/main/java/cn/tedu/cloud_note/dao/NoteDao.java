package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
     // public List<Note> findByBookId(String bookId);
        // �����淽��һ������һ��Noteʵ����ļ�������ȫ���Եġ������Noteʵ�����еĴ󲿷ֳ�Ա�����洢����Ϣ���Ƕ��ò�����
        // �������ﳢ��ʹ��Map������Ϊ����ֵ��ֻ�洢��Ҫ�õ����ֶμ���
        public List<Map> findByBookId(String bookId);
        
        // ����һ�����������ڸ��ݱʼ�id��ѯ�ʼ�����
        public Note findByNoteId(String noteId);
        
        // ����һ�����������ڱ���ʼ�����
        public int updateNote(Note note);
        
        // ����һ�����������ڴ����µıʼ�
        public int addNote(Note note);
}
