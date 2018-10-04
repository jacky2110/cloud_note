package test.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestBookDao {
        
        private BookDao dao;
        
        @Before
        public void init() {
                // ����ж�������ļ������
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml"};
                
                // ����spring����
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                // ��ȡMapperӳ�������ӿڣ�
                dao = ctx.getBean("bookDao", BookDao.class);
        }
        
        @Test
        public void testDao() {
                List<Book> result = dao.findByUserId("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
                
                for (Book book : result) {
                        System.out.println(book.getCn_notebook_name());
                }
        }
        
        @Test
        public void testAddBook() {
                System.out.println("testAddBook()====================================");
                
                // ����һ���ʼ�id
                String bookId = NoteUtil.createId();
                
                // ����һ��timestamp
                Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Long�ͺ�����תΪTimestamp�ͣ���ֱ�Ӵ洢�����ݿ�  
                System.out.println("Timestamp:" + timestamp);  
                
                
                // ����Book����
                Book book = new Book();
                book.setCn_notebook_id(bookId);
                book.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");  // ��mysql�в鵽���û�demo��userId
                book.setCn_notebook_type_id("5");
                book.setCn_notebook_name("testAddBook");
                book.setCn_notebook_desc("No Description");
                book.setCn_notebook_createtime(timestamp);
                
                System.out.println("cn_notebook_id:         " + book.getCn_notebook_id());
                System.out.println("cn_user_id:             " + book.getCn_user_id());
                System.out.println("cn_notebook_type_id:    " + book.getCn_notebook_type_id());
                System.out.println("cn_notebook_name:       " + book.getCn_notebook_name());
                System.out.println("cn_notebook_desc:       " + book.getCn_notebook_desc());
                System.out.println("cn_notebook_createtime: " + book.getCn_notebook_createtime());
                
                // ���ʼǱ��������ݿ�
                int row = dao.addBook(book);
                
                System.out.println("��Ӱ�����ݵ�����: " + row);
                
                // ����userId��ѯ�ʼǱ�
                List<Book> result = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
                for (Book b : result) {
                        System.out.println(b.getCn_notebook_name());
                }
        }
}
