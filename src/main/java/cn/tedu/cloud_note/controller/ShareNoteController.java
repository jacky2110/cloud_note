package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

//����Ķ���ǰ��� @Controllerע�ͣ�����֪ͨspring�����Զ�Ϊ���ഴ��һ��beanԪ��
//������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
@Controller
//@RequestMapping("/usr")���������ָ���˸�·����������ľ�Ӧ��д��@RequestMapping("/login.do")
public class ShareNoteController {

        @Resource(name="id_ShareServiceImpl") // ʹ��ע�ͽ�������ע��
        private ShareService shareservice;
        
        @RequestMapping("/share/add.do")  // ָ���÷������ڴ�������·��"/json1.do"
        @ResponseBody    // �����ؽ����Ϊ����ֱ��ʹ�ã���ֹ����ֵ��������ͼ����������ת��
        public NoteResult<Share> addBook(String noteId) {
                // �����еĲ������Ʋ�������ȡ������Ҫ��jQuery����ajax����ʱ��data���е�������һ�£�
                // $.ajax({...data:{"name":xxx, "password":xxx, "nick":xxx},...});
                // �ڲ�ʹ��ajaxʱ������������Ҫ���ύ���ݵĿؼ���name������һ��
                
                return shareservice.shareNote(noteId);
        }
}
