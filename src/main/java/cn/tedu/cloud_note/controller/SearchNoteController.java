package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

//在类的定义前添加 @Controller注释，即可通知spring容器自动为该类创建一个bean元素
//不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
@Controller
//@RequestMapping("/usr")如果在这里指定了父路径，则下面的就应该写作@RequestMapping("/login.do")
public class SearchNoteController {
        @Resource(name="id_ShareServiceImpl") // 使用注释进行依赖注入
        private ShareService shareservice;
        
        @RequestMapping("/share/search.do")  // 指定该方法用于处理请求路径"/json1.do"
        @ResponseBody    // 将返回结果作为数据直接使用（防止返回值被当做视图名而进行跳转）
        public NoteResult<List<Share>> searchNote(String keyword, int page) {
                
                return shareservice.searchNoteMap(keyword, page);
        }
}
