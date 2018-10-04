package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

//����Ķ���ǰ��� @Controllerע�ͣ�����֪ͨspring�����Զ�Ϊ���ഴ��һ��beanԪ��
//������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
@Controller
//@RequestMapping("/usr")���������ָ���˸�·����������ľ�Ӧ��д��@RequestMapping("/login.do")
public class LoadBooksController {
        
        @Resource(name="id_BookServiceImpl") // ʹ��ע�ͽ�������ע��
        private BookService bookservice;
        
        @RequestMapping("/book/loadBooks.do")  // ָ���÷������ڴ�������·��"/json1.do"
        @ResponseBody    // �����ؽ����Ϊ����ֱ��ʹ�ã���ֹ����ֵ��������ͼ����������ת��
        public NoteResult<List<Book>> execute(String userId) {
                // �����еĲ������Ʋ�������ȡ������Ҫ��jQuery����ajax����ʱ��data���е�������һ�£�
                // $.ajax({...data:{"name":xxx, "password":xxx, "nick":xxx},...});
                // �ڲ�ʹ��ajaxʱ������������Ҫ���ύ���ݵĿؼ���name������һ��
                
                return bookservice.loadUserBooks(userId);
        }
}
