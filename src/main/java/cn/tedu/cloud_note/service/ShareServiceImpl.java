package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.SelectLimit;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//例如这里是业务层的实现类，因此应该使用@Service   
@Service("id_ShareServiceImpl")    // 如果不指定id，则默认的id为首字母小写的类名
public class ShareServiceImpl implements ShareService {

        // 使用注释进行依赖注入
        @Resource(name="noteDao")
        private NoteDao noteDao;  // 用于根据noteId从cn_note表中查询笔记信息
        
        // 使用注释进行依赖注入
        @Resource(name="shareDao")
        private ShareDao shareDao;  // 用于将查到的笔记信息插入cn_share表中
        
        // 使用Spring注释读取配置文件中的数据
        @Value("#{id_Config.selectRows}")
        private Integer selectRows;
        
        public NoteResult<Share> shareNote(String noteId) {
                
                // 创建Note对象
                Note note = noteDao.findByNoteId(noteId);
                
                // 创建Share类
                Share share = new Share();
                
                String shareId = NoteUtil.createId();
                String shareTitle = note.getCn_note_title();
                String shareBody = note.getCn_note_body();
                
                share.setCn_share_id(shareId);
                share.setCn_share_title(shareTitle);
                share.setCn_share_body(shareBody);
                share.setCn_note_id(noteId);
                
                // 将Share类对象存入数据库
                int row = shareDao.shareNote(share);
                
                // 构架返回对象
                NoteResult<Share> result = new NoteResult<Share>();
                
                if (row == 1) {
                        result.setStatus(0);  // 设置状态为0，表示插入成功
                        result.setMsg("分享笔记成功");  // 设置返回消息
                        result.setData(share);  // 将查询结果添加到返回结果中
                } else {
                        result.setStatus(1);  // 设置状态为1，表示插入失败
                        result.setMsg("分享笔记失败");  // 设置返回消息
                        result.setData(null);  // 将查询结果添加到返回结果中
                }
                
                return result;
        }

        public NoteResult<List<Share>> searchNote(String keyword, int page) {
                
                System.out.println("selectRows: " + selectRows);
                
                // 将关键词变为模糊搜索
                keyword = "%" + keyword + "%";
                // 计算查询结果的的offset和rows
                Integer rows = selectRows;  // 每次查询2行记录
                Integer offset = (page - 1) * rows;
                
                
                SelectLimit sl = new SelectLimit();
                sl.setKeyword(keyword);
                sl.setOffset(offset);
                sl.setRows(rows);
                
                // 搜索关键字
                List<Share> list = shareDao.findLikeTitle(sl);
                
                // 构建返回结果
                NoteResult<List<Share>> result = new NoteResult<List<Share>>();
                result.setStatus(0);  // 设置状态为0，表示插入成功
                result.setMsg("搜索完毕");  // 设置返回消息
                result.setData(list);  // 将查询结果添加到返回结果中
                
                return result;
        }

        public NoteResult<List<Share>> searchNoteMap(String keyword, int page) {
                
                // 将关键词变为模糊搜索
                keyword = "%" + keyword + "%";
                // 计算查询结果的的offset和rows
                Integer rows = selectRows;  // 每次查询2行记录
                Integer offset = (page - 1) * rows;
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keyword", "%测试%");
                map.put("offset", offset);
                map.put("rows", rows);
                
                // 搜索关键词，注意：如果关键词不加%号则表示要完全匹配
                List<Share> list = shareDao.findLikeTitleMap(map);
                
                // 构建返回结果
                NoteResult<List<Share>> result = new NoteResult<List<Share>>();
                result.setStatus(0);  // 设置状态为0，表示插入成功
                result.setMsg("搜索完毕");  // 设置返回消息
                result.setData(list);  // 将查询结果添加到返回结果中
                
                return result;
        }
}
