package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

//在类的定义前添加 @Controller注释，即可通知spring容器自动为该类创建一个bean元素
//不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
@Controller
//@RequestMapping("/usr")如果在这里指定了父路径，则下面的就应该写作@RequestMapping("/login.do")
public class LoadBooksController {
        
        @Resource(name="id_BookServiceImpl") // 使用注释进行依赖注入
        private BookService bookservice;
        
        @RequestMapping("/book/loadBooks.do")  // 指定该方法用于处理请求路径"/json1.do"
        @ResponseBody    // 将返回结果作为数据直接使用（防止返回值被当做视图名而进行跳转）
        public NoteResult<List<Book>> execute(String userId) {
                // 函数中的参数名称不能随意取，必须要与jQuery发送ajax数据时的data段中的属性名一致：
                // $.ajax({...data:{"name":xxx, "password":xxx, "nick":xxx},...});
                // 在不使用ajax时，参数名必须要与提交数据的控件的name属性名一致
                
                return bookservice.loadUserBooks(userId);
        }
}
