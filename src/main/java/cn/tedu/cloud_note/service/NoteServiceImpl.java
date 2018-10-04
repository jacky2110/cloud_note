package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������ҵ����ʵ���࣬���Ӧ��ʹ��@Service   
@Service("id_NoteServiceImpl")    // �����ָ��id����Ĭ�ϵ�idΪ����ĸСд������
public class NoteServiceImpl implements NoteService {

        // ʹ��ע�ͽ�������ע��
        @Resource(name="noteDao")
        private NoteDao noteDao;
        

        public NoteResult<List<Map>> loadBookNotes(String bookId) {
                // ����Dao�㷽������ѯ���ݿ�
                List<Map> list = noteDao.findByBookId(bookId);
                
                // �������ؽ��
                NoteResult<List<Map>> result = new NoteResult<List<Map>>();
                result.setStatus(0);  // ����״̬Ϊ0����ʾ��ѯ�ɹ�
                result.setMsg("��ѯ�ʼǳɹ�");  // ���÷�����Ϣ
                result.setData(list);  // ����ѯ�����ӵ����ؽ����
                
                return result;
        }


        public NoteResult<Note> loadNote(String noteId) {
                // ����Dao�㷽������ѯ���ݿ�
                Note note = noteDao.findByNoteId(noteId);
                
                // �������ؽ��
                NoteResult<Note> result = new NoteResult<Note>();
                
                if (note == null) {
                        result.setStatus(1);  // ����״̬Ϊ1����ʾ��ѯʧ��
                        result.setMsg("δ�ҵ��ʼ�����");  // ���÷�����Ϣ
                        return result;
                } else {
                        result.setStatus(0);  // ����״̬Ϊ0����ʾ��ѯ�ɹ�
                        result.setMsg("��ѯ�ɹ�");  // ���÷�����Ϣ
                        result.setData(note);  // ����ѯ�����ӵ����ؽ����
                        return result;
                }
        }


        public NoteResult<Object> saveNote(String noteId, String noteTitle, String noteBody) {
                
                System.out.println("NoteServiceImpl.saveNote()");
                System.out.println("noteId:    " + noteId);
                System.out.println("noteTitle: " + noteTitle);
                System.out.println("noteBody:  " + noteBody);
                
                // ����Note����
                Note note = new Note();
                note.setCn_note_id(noteId);
                note.setCn_note_title(noteTitle);
                note.setCn_note_body(noteBody);
                note.setCn_note_last_modify_time(System.currentTimeMillis());
                
                // ��Note����洢�����ݿ�
                int count = noteDao.updateNote(note);
                
                // �������ؽ��
                NoteResult<Object> result = new NoteResult<Object>();
                
                if (count == 1) {
                        result.setStatus(0);  // ����״̬Ϊ0����ʾ���³ɹ�
                        result.setMsg("�ʼǱ���ɹ�");  // ���÷�����Ϣ
                        result.setData(1);
                        return result;
                } else {
                        result.setStatus(1);  // ����״̬Ϊ0����ʾ����ʧ��
                        result.setMsg("�ʼǱ���ʧ��");  // ���÷�����Ϣ
                        result.setData(0);
                        return result;
                }
        }


        public NoteResult<Note> addNote(String userId, String bookId, String noteTitle) {
                System.out.println("[NSIAD] NoteServiceImpl.addNote()====================================");
                
                // ����Note����
                Note note = new Note();
                
                String noteId = NoteUtil.createId();  // ����noteId
                String noteStatusId = "1";
                String noteType = "5";
                String noteBody = "";
                long createTime = System.currentTimeMillis();
                
                note.setCn_note_id(noteId);
                note.setCn_notebook_id(bookId);
                note.setCn_user_id(userId);
                note.setCn_note_status_id(noteStatusId);
                note.setCn_note_type_id(noteType);
                note.setCn_note_title(noteTitle);
                note.setCn_note_body(noteBody);
                note.setCn_note_create_time(createTime);
                note.setCn_note_last_modify_time(createTime);
                
                System.out.println("[NSIAD] noteId:      " + note.getCn_note_id());
                System.out.println("[NSIAD] bookId:      " + note.getCn_notebook_id());
                System.out.println("[NSIAD] userId:      " + note.getCn_user_id());
                System.out.println("[NSIAD] noteStatusId:" + note.getCn_note_status_id());
                System.out.println("[NSIAD] noteType:    " + note.getCn_note_type_id());
                System.out.println("[NSIAD] noteTitle:   " + note.getCn_note_title());
                System.out.println("[NSIAD] noteBody:    " + note.getCn_note_body());
                System.out.println("[NSIAD] createTime:  " + note.getCn_note_create_time());
                System.out.println("[NSIAD] modifyTime:  " + note.getCn_note_last_modify_time());
                
                // ��Note���󱣴浽���ݿ���
                int row = noteDao.addNote(note);
                
                // �������ؽ��
                NoteResult<Note> result = new NoteResult<Note>();
                
                if (row == 1) {
                        result.setStatus(0);  // ����״̬Ϊ0����ʾ����ɹ�
                        result.setMsg("�����ʼǳɹ�");  // ���÷�����Ϣ
                        result.setData(note);  // ��Note���󸽼ӵ�����ֵ��
                        return result;
                } else {
                        result.setStatus(1);  // ����״̬Ϊ0����ʾ����ʧ��
                        result.setMsg("�����ʼǳɹ�");  // ���÷�����Ϣ
                        return result;                        
                }
        }
}
