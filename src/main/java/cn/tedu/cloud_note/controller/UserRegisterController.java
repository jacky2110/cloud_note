package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

//����Ķ���ǰ��� @Controllerע�ͣ�����֪ͨspring�����Զ�Ϊ���ഴ��һ��beanԪ��
//������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
@Controller
//@RequestMapping("/usr")���������ָ���˸�·����������ľ�Ӧ��д��@RequestMapping("/login.do")
public class UserRegisterController {

        @Resource(name="id_UserServiceImpl") // ʹ��ע�ͽ�������ע��
        private UserService userservice;
        
        @RequestMapping("/user/register.do")  // ָ���÷������ڴ�������·��"/json1.do"
        @ResponseBody    // �����ؽ����Ϊ����ֱ��ʹ�ã���ֹ����ֵ��������ͼ����������ת��
        public NoteResult<Object> execute(String name, String password, String nick) {
                // �����еĲ������Ʋ�������ȡ������Ҫ��jQuery����ajax����ʱ��data���е�������һ�£�
                // $.ajax({...data:{"name":xxx, "password":xxx, "nick":xxx},...});
                // �ڲ�ʹ��ajaxʱ������������Ҫ���ύ���ݵĿؼ���name������һ��
                
                System.out.println(name+","+password+","+nick);
                
                // ����UserService�����½����
                NoteResult<Object> result = userservice.addUser(name, password, nick);
                
                return result;
        }
}
