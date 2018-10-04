package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase {
        private BookService bookService;
        
        @Before
        public void init() {
                // ���ø���ķ�����ȡ������ʵ����Ȼ���ȡMapperӳ������beanʵ��
                bookService = super.getContext().getBean("id_BookServiceImpl", BookService.class);
        }
        
        @Test
        public void test() {
                // ����Service�㺯������ȡ�ʼ��б�
                NoteResult<List<Book>> result = bookService.loadUserBooks("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                
                // ����б�
                for (Book book : result.getData()) {
                        System.out.println(book.getCn_notebook_name());
                }
        }
        
        @Test
        public void testAddBook() {
                System.out.println("TestBookService.testAddBook()============================");
                
                // ����Service�㺯��������һ����¼���±ʼǱ������ݣ�
                NoteResult<Book> result = bookService.addBook("48595f52-b22c-4485-9244-f4004255b972", "TestBookService.testAddBook");
                
                System.out.println("Status: " + result.getStatus());
                System.out.println("   Msg: " + result.getMsg());
                System.out.println("  Data: " + result.getData());
        }
}
