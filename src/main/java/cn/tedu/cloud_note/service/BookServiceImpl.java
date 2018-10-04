package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������ҵ����ʵ���࣬���Ӧ��ʹ��@Service   
@Service("id_BookServiceImpl")    // �����ָ��id����Ĭ�ϵ�idΪ����ĸСд������
public class BookServiceImpl implements BookService {

        // ʹ��ע�ͽ�������ע��
        @Resource(name="bookDao")
        private BookDao bookDao;
        
        public NoteResult<List<Book>> loadUserBooks(String userId) {
                // ����Dao�㷽������ѯ���ݿ�
                List<Book> list = bookDao.findByUserId(userId);
                
                // �������ؽ��
                NoteResult<List<Book>> result = new NoteResult<List<Book>>();
                result.setStatus(0);  // ����״̬Ϊ0����ʾ��ѯ�ɹ�
                result.setMsg("��ѯ�ʼǱ��ɹ�");  // ���÷�����Ϣ
                result.setData(list);  // ����ѯ�����ӵ����ؽ����
                
                return result;
        }

        public NoteResult<Book> addBook(String userId, String bookName) {
                System.out.println("BookServiceImpl.addBook()====================================");
                
                // ����һ���ʼ�id
                String bookId = NoteUtil.createId();
                
                // ����һ��timestamp
                Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Long�ͺ�����תΪTimestamp�ͣ���ֱ�Ӵ洢�����ݿ�  
                System.out.println("Timestamp:" + timestamp);  
                
                // ����Book����
                Book book = new Book();
                book.setCn_notebook_id(bookId);
                book.setCn_user_id(userId);  // ��mysql�в鵽���û�demo��userId
                book.setCn_notebook_type_id("5");
                book.setCn_notebook_name(bookName);
                book.setCn_notebook_desc("No Description");
                book.setCn_notebook_createtime(timestamp);
                
                System.out.println("cn_notebook_id:         " + book.getCn_notebook_id());
                System.out.println("cn_user_id:             " + book.getCn_user_id());
                System.out.println("cn_notebook_type_id:    " + book.getCn_notebook_type_id());
                System.out.println("cn_notebook_name:       " + book.getCn_notebook_name());
                System.out.println("cn_notebook_desc:       " + book.getCn_notebook_desc());
                System.out.println("cn_notebook_createtime: " + book.getCn_notebook_createtime());
                
                // ���ʼǱ��������ݿ�
                int row = bookDao.addBook(book);
                System.out.println("��Ӱ�����ݵ�����: " + row);
                
//                // ����userId��ѯ�ʼǱ�
//                List<Book> list = bookDao.findByUserId(bookName);
//                for (Book b : list) {
//                        System.out.println(b.getCn_notebook_name());
//                }
                
                // �������ؽ��
                NoteResult<Book> result = new NoteResult<Book>();
                
                // ������ݿ�����ɹ�
                if (row == 1) {
                        result.setStatus(0);  // ����״̬Ϊ0����ʾ����ɹ�
                        result.setMsg("�����ʼǱ��ɹ�");  // ���÷�����Ϣ
                        result.setData(book);  // ����ѯ�����ӵ����ؽ����
                } else {
                        result.setStatus(1);  // ����״̬Ϊ1����ʾ����ʧ��
                        result.setMsg("�����ʼǱ�ʧ��");  // ���÷�����Ϣ
                        result.setData(null);  // ����ѯ�����ӵ����ؽ����
                }
                
                return result;
        }
}
