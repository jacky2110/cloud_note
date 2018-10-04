package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;

public interface UserService {
        // ����һ�����������ڱȶ��û�������˺ź������Ƿ���ȷ
        public NoteResult<User> checkLogin(String name, String password);
        
        // ����һ�����������������ݿ��в���һ����¼
        // ���ڲ�����������ؾ������ͣ�����ʹ��Object����
        public NoteResult<Object> addUser(String name, String password, String nick);
}
