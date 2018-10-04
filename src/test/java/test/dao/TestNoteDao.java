package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteUtil;
import test.TestBase;

public class TestNoteDao extends TestBase {

        private NoteDao dao;
        
        @Before
        public void init() {
                dao = super.getContext().getBean("noteDao", NoteDao.class);
        }
        
        @Test
        public void test() {
                List<Map> result = dao.findByBookId("4b86d1f9-6345-4532-bc50-ee86442f004b");
                
                for (Map map : result) {
                        System.out.println(map);
                }
                for (Map map : result) {
                        System.out.println(map.get("cn_note_id"));
                }
                for (Map map : result) {
                        System.out.println(map.get("cn_note_title"));
                }
        }
        
        @Test
        public void testFindNote() {
                Note note = dao.findByNoteId("60480071-f989-4945-9b1c-0d2aba07ae96");
                
                System.out.println("id: " + note.getCn_note_id());
                System.out.println("title: " + note.getCn_note_title());
                System.out.println("body: " + note.getCn_note_body());
                System.out.println(note);
        }
        
        @Test
        public void testSaveNote() {
                System.out.println("testSaveNote()==========================");
                
                // 查找一篇笔记
                Note note = dao.findByNoteId("0c8b7f7e-0336-4220-9774-fd97d2cd0c40");
                System.out.println("id:    " + note.getCn_note_id());
                System.out.println("title: " + note.getCn_note_title());
                System.out.println("body:  " + note.getCn_note_body());
                System.out.println("time:  " + note.getCn_note_last_modify_time());
                
                // 模拟对修改的笔记
                note.setCn_note_title(note.getCn_note_title() + "1");
                note.setCn_note_body(note.getCn_note_body() + "1");
                note.setCn_note_last_modify_time(System.currentTimeMillis());  // 获取系统当前时间
                
                // 将更改后的笔记保存到数据库中
                int count = dao.updateNote(note);
                System.out.println("count = " + count);
                
                // 再次查询该笔记
                note = dao.findByNoteId("0c8b7f7e-0336-4220-9774-fd97d2cd0c40");
                System.out.println("id:    " + note.getCn_note_id());
                System.out.println("title: " + note.getCn_note_title());
                System.out.println("body:  " + note.getCn_note_body());
                System.out.println("time:  " + note.getCn_note_last_modify_time());
        }
        
        @Test
        public void testAddNote() {
                System.out.println("testAddNote()==========================");
                
                // 构造一个Note对象
                Note note = new Note();
                
                String noteId = NoteUtil.createId();  // 创建noteId
                String notebookId = "81fe20a1-defc-41da-b248-764466b2b2e9";
                String userId = "48595f52-b22c-4485-9244-f4004255b972";
                String noteStatusId = "1";
                String noteType = "5";
                String noteTitle = "TestNoteDao.testAddNote.noteTitle";
                String noteBody = "";
                long createTime = System.currentTimeMillis();
                long modifyTime = System.currentTimeMillis();
                
                note.setCn_note_id(noteId);
                note.setCn_notebook_id(notebookId);
                note.setCn_user_id(userId);
                note.setCn_note_status_id(noteStatusId);
                note.setCn_note_type_id(noteType);
                note.setCn_note_title(noteTitle);
                note.setCn_note_body(noteBody);
                note.setCn_note_create_time(createTime);
                note.setCn_note_last_modify_time(modifyTime);
                
                System.out.println("noteId:      " + note.getCn_note_id());
                System.out.println("notebookId:  " + note.getCn_notebook_id());
                System.out.println("userId:      " + note.getCn_user_id());
                System.out.println("noteStatusId:" + note.getCn_note_status_id());
                System.out.println("noteType:    " + note.getCn_note_type_id());
                System.out.println("noteTitle:   " + note.getCn_note_title());
                System.out.println("noteBody:    " + note.getCn_note_body());
                System.out.println("createTime:  " + note.getCn_note_create_time());
                System.out.println("modifyTime:  " + note.getCn_note_last_modify_time());
                
                // 将Note对象插入到数据库中
                dao.addNote(note);
                
                // 查询更插入的记录
                note = dao.findByNoteId(noteId);
                
                System.out.println("noteId:      " + note.getCn_note_id());
                System.out.println("notebookId:  " + note.getCn_notebook_id());
                System.out.println("userId:      " + note.getCn_user_id());
                System.out.println("noteStatusId:" + note.getCn_note_status_id());
                System.out.println("noteType:    " + note.getCn_note_type_id());
                System.out.println("noteTitle:   " + note.getCn_note_title());
                System.out.println("noteBody:    " + note.getCn_note_body());
                System.out.println("createTime:  " + note.getCn_note_create_time());
                System.out.println("modifyTime:  " + note.getCn_note_last_modify_time());
        }
}
