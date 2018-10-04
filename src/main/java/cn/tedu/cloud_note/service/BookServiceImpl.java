package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//例如这里是业务层的实现类，因此应该使用@Service   
@Service("id_BookServiceImpl")    // 如果不指定id，则默认的id为首字母小写的类名
public class BookServiceImpl implements BookService {

        // 使用注释进行依赖注入
        @Resource(name="bookDao")
        private BookDao bookDao;
        
        public NoteResult<List<Book>> loadUserBooks(String userId) {
                // 调用Dao层方法，查询数据库
                List<Book> list = bookDao.findByUserId(userId);
                
                // 构建返回结果
                NoteResult<List<Book>> result = new NoteResult<List<Book>>();
                result.setStatus(0);  // 设置状态为0，表示查询成功
                result.setMsg("查询笔记本成功");  // 设置返回消息
                result.setData(list);  // 将查询结果添加到返回结果中
                
                return result;
        }

        public NoteResult<Book> addBook(String userId, String bookName) {
                System.out.println("BookServiceImpl.addBook()====================================");
                
                // 构造一个笔记id
                String bookId = NoteUtil.createId();
                
                // 构造一个timestamp
                Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Long型毫秒数转为Timestamp型，可直接存储进数据库  
                System.out.println("Timestamp:" + timestamp);  
                
                // 构建Book对象
                Book book = new Book();
                book.setCn_notebook_id(bookId);
                book.setCn_user_id(userId);  // 在mysql中查到的用户demo的userId
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
                
                // 将笔记本插入数据库
                int row = bookDao.addBook(book);
                System.out.println("受影响数据的行数: " + row);
                
//                // 根据userId查询笔记本
//                List<Book> list = bookDao.findByUserId(bookName);
//                for (Book b : list) {
//                        System.out.println(b.getCn_notebook_name());
//                }
                
                // 构建返回结果
                NoteResult<Book> result = new NoteResult<Book>();
                
                // 如果数据库操作成功
                if (row == 1) {
                        result.setStatus(0);  // 设置状态为0，表示插入成功
                        result.setMsg("创建笔记本成功");  // 设置返回消息
                        result.setData(book);  // 将查询结果添加到返回结果中
                } else {
                        result.setStatus(1);  // 设置状态为1，表示插入失败
                        result.setMsg("创建笔记本失败");  // 设置返回消息
                        result.setData(null);  // 将查询结果添加到返回结果中
                }
                
                return result;
        }
}
