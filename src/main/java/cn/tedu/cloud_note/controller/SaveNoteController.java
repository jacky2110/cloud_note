package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

//����Ķ���ǰ��� @Controllerע�ͣ�����֪ͨspring�����Զ�Ϊ���ഴ��һ��beanԪ��
//������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
@Controller
//@RequestMapping("/usr")���������ָ���˸�·����������ľ�Ӧ��д��@RequestMapping("/login.do")
public class SaveNoteController {

        @Resource(name="id_NoteServiceImpl") // ʹ��ע�ͽ�������ע��
        private NoteService noteservice;
        
        @RequestMapping("/note/save.do")  // ָ���÷������ڴ�������·��"/note/save.do"
        @ResponseBody    // �����ؽ����Ϊ����ֱ��ʹ�ã���ֹ����ֵ��������ͼ����������ת��
        public NoteResult<Object> saveNote(String noteId, String noteTitle, String noteBody) {
                // �����еĲ������Ʋ�������ȡ������Ҫ��jQuery����ajax����ʱ��data���е�������һ�£�
                // $.ajax({...data:{"name":xxx, "password":xxx, "nick":xxx},...});
                // �ڲ�ʹ��ajaxʱ������������Ҫ���ύ���ݵĿؼ���name������һ��
                
                System.out.println("SaveNoteController.saveNote()");
                System.out.println("noteId:    " + noteId);
                System.out.println("noteTitle: " + noteTitle);
                System.out.println("noteBody:  " + noteBody);
                
                return noteservice.saveNote(noteId, noteTitle, noteBody);
        }
}
