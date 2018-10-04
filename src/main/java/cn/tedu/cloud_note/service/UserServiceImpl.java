package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������ҵ����ʵ���࣬���Ӧ��ʹ��@Service   
@Service("id_UserServiceImpl")    // �����ָ��id����Ĭ�ϵ�idΪ����ĸСд������
public class UserServiceImpl implements UserService {

        // ʹ��ע�ͽ�������ע��
        @Resource(name="userDao")
        private UserDao userDao;
        
        public NoteResult<User> checkLogin(String name, String password) {
                NoteResult<User> result = new NoteResult<User>();
                
                // ������name��ѯ���ݿ�
                User user = userDao.findByName(name);
                
                // ��������ݿ��в�ѯ�������û�
                if (user == null) {
                        result.setStatus(1);  // �Զ����Լ���������ʾʧ��
                        result.setMsg("�û���������");
                        return result;
                }
                
                // �ܲ�ѯ���û��󣬶����������֤
                String md5Password = NoteUtil.md5(password);
                if (user.getCn_user_password().equals(md5Password) == false) {
                        result.setStatus(2);  // �Զ����Լ���������ʾʧ��
                        result.setMsg("���벻��ȷ");
                        return result;
                }
                
                // �ܵ������ʾ�˺�����ͨ����֤
                result.setStatus(0);  // �Զ����Լ�������ʾ�ɹ�
                result.setMsg("��¼�ɹ�");
                result.setData(user);
                return result;
        }

        public NoteResult<Object> addUser(String name, String password, String nick) {
                
                NoteResult<Object> result = new NoteResult<Object>();
                
                // ����û����Ƿ�ռ��
                User userFindByname = userDao.findByName(name);
                if (userFindByname != null) {  // ������ѵ��������ʾ�û����Ѿ���ռ��
                        result.setStatus(1);  // ����״̬Ϊ1����ʾ�û����Ѿ���ռ��
                        result.setMsg("�û����Ѿ���ռ��");
                        return result;
                }
                        
                // ����Ѳ����������ʾ�û���û�б�ռ�ã���׼���û���Ϣ
                User user = new User();
                user.setCn_user_id(NoteUtil.createId());
                user.setCn_user_name(name);
                user.setCn_user_password(NoteUtil.md5(password));
                user.setCn_user_nick(nick);
                
                // ���û���Ϣ�������ݿ�
                userDao.save(user);
                
                // ׼�������ؽ��
                result.setStatus(0);  // ����״̬Ϊ1����ʾ�洢�ɹ�
                result.setMsg("ע��ɹ�");
                return result;
        }
}
