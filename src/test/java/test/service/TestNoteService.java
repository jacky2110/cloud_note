package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase {
        private NoteService noteService;
        
        @Before
        public void init() {
                // ���ø���ķ�����ȡ������ʵ����Ȼ���ȡMapperӳ������beanʵ��
                noteService = super.getContext().getBean("id_NoteServiceImpl", NoteService.class);
        }
        
        @Test
        public void test() {
                // ����Service�㺯������ȡ�ʼ��б�
                NoteResult<List<Map>> result = noteService.loadBookNotes("4b86d1f9-6345-4532-bc50-ee86442f004b");
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                
                // ����б�
                for (Map map : result.getData()) {
                        System.out.println(map);
                }
                for (Map map : result.getData()) {
                        System.out.println(map.get("cn_note_id"));
                }
                for (Map map : result.getData()) {
                        System.out.println(map.get("cn_note_title"));
                }
        }
        
        @Test
        public void testGetNoteContent() {
                // ����Service�㺯������ȡ�ʼ��б�
                NoteResult<Note> result = noteService.loadNote("60480071-f989-4945-9b1c-0d2aba07ae96");
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
        }
        
        @Test
        public void testSaveNote() {
                System.out.println("testSaveNote()=====================");
                
                // ����Service�㺯������ȡ�ʼ�����
                NoteResult<Note> result = noteService.loadNote("0c8b7f7e-0336-4220-9774-fd97d2cd0c40");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
                
                // ��ȡ���ؽ���е�Note����
                Note note = result.getData();
                
                String noteId    = note.getCn_note_id();
                String noteTitle = note.getCn_note_title() + "2";
                String noteBody  = note.getCn_note_body() + "2";
                
                // ��Note���󱣴浽���ݿ�
                NoteResult<Object> resultObject = noteService.saveNote(noteId, noteTitle, noteBody);
                System.out.println("-----------------------------------");
                System.out.println("status: " + resultObject.getStatus());
                System.out.println("Msg:    " + resultObject.getMsg());
                System.out.println("Data:   " + resultObject.getData());
                
                // �ٴε���Service�㺯������ȡ�ʼ�����
                result = noteService.loadNote("0c8b7f7e-0336-4220-9774-fd97d2cd0c40");
                System.out.println("+++++++++++++++++++++++++++++++++++");
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
        }
        
        @Test
        public void testAddNote() {
                System.out.println("[TNSTAN] TestNoteService.testAddNote()=====================");
                
                String userId = "48595f52-b22c-4485-9244-f4004255b972";
                String bookId = "81fe20a1-defc-41da-b248-764466b2b2e9";
                String noteTitle = "TestNoteService.testAddNote.noteTitle";
                
                // ����Service�㺯���������ʼǣ��������ݿ��¼��
                NoteResult<Note> result = noteService.addNote(userId, bookId, noteTitle);
                
                System.out.println("[TNSTAN] -----------------------------------");
                System.out.println("[TNSTAN] status: " + result.getStatus());
                System.out.println("[TNSTAN] Msg:    " + result.getMsg());
                System.out.println("[TNSTAN] Data:   " + result.getData());
                
                // �ٴε���Service�㺯������ȡ�ʼ�����
                result = noteService.loadNote(result.getData().getCn_note_id());
                System.out.println("[TNSTAN] +++++++++++++++++++++++++++++++++++");
                System.out.println("[TNSTAN] status: " + result.getStatus());
                System.out.println("[TNSTAN] Msg:    " + result.getMsg());
                System.out.println("[TNSTAN] Data:   " + result.getData());
        }
}
