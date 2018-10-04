package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//例如这里是业务层的实现类，因此应该使用@Service   
@Service("id_NoteServiceImpl")    // 如果不指定id，则默认的id为首字母小写的类名
public class NoteServiceImpl implements NoteService {

        // 使用注释进行依赖注入
        @Resource(name="noteDao")
        private NoteDao noteDao;
        

        public NoteResult<List<Map>> loadBookNotes(String bookId) {
                // 调用Dao层方法，查询数据库
                List<Map> list = noteDao.findByBookId(bookId);
                
                // 构建返回结果
                NoteResult<List<Map>> result = new NoteResult<List<Map>>();
                result.setStatus(0);  // 设置状态为0，表示查询成功
                result.setMsg("查询笔记成功");  // 设置返回消息
                result.setData(list);  // 将查询结果添加到返回结果中
                
                return result;
        }


        public NoteResult<Note> loadNote(String noteId) {
                // 调用Dao层方法，查询数据库
                Note note = noteDao.findByNoteId(noteId);
                
                // 构建返回结果
                NoteResult<Note> result = new NoteResult<Note>();
                
                if (note == null) {
                        result.setStatus(1);  // 设置状态为1，表示查询失败
                        result.setMsg("未找到笔记数据");  // 设置返回消息
                        return result;
                } else {
                        result.setStatus(0);  // 设置状态为0，表示查询成功
                        result.setMsg("查询成功");  // 设置返回消息
                        result.setData(note);  // 将查询结果添加到返回结果中
                        return result;
                }
        }


        public NoteResult<Object> saveNote(String noteId, String noteTitle, String noteBody) {
                
                System.out.println("NoteServiceImpl.saveNote()");
                System.out.println("noteId:    " + noteId);
                System.out.println("noteTitle: " + noteTitle);
                System.out.println("noteBody:  " + noteBody);
                
                // 创建Note对象
                Note note = new Note();
                note.setCn_note_id(noteId);
                note.setCn_note_title(noteTitle);
                note.setCn_note_body(noteBody);
                note.setCn_note_last_modify_time(System.currentTimeMillis());
                
                // 将Note对象存储到数据库
                int count = noteDao.updateNote(note);
                
                // 构建返回结果
                NoteResult<Object> result = new NoteResult<Object>();
                
                if (count == 1) {
                        result.setStatus(0);  // 设置状态为0，表示更新成功
                        result.setMsg("笔记保存成功");  // 设置返回消息
                        result.setData(1);
                        return result;
                } else {
                        result.setStatus(1);  // 设置状态为0，表示更新失败
                        result.setMsg("笔记保存失败");  // 设置返回消息
                        result.setData(0);
                        return result;
                }
        }


        public NoteResult<Note> addNote(String userId, String bookId, String noteTitle) {
                System.out.println("[NSIAD] NoteServiceImpl.addNote()====================================");
                
                // 创建Note对象
                Note note = new Note();
                
                String noteId = NoteUtil.createId();  // 创建noteId
                String noteStatusId = "1";
                String noteType = "5";
                String noteBody = "";
                long createTime = System.currentTimeMillis();
                
                note.setCn_note_id(noteId);
                note.setCn_notebook_id(bookId);
                note.setCn_user_id(userId);
                note.setCn_note_status_id(noteStatusId);
                note.setCn_note_type_id(noteType);
                note.setCn_note_title(noteTitle);
                note.setCn_note_body(noteBody);
                note.setCn_note_create_time(createTime);
                note.setCn_note_last_modify_time(createTime);
                
                System.out.println("[NSIAD] noteId:      " + note.getCn_note_id());
                System.out.println("[NSIAD] bookId:      " + note.getCn_notebook_id());
                System.out.println("[NSIAD] userId:      " + note.getCn_user_id());
                System.out.println("[NSIAD] noteStatusId:" + note.getCn_note_status_id());
                System.out.println("[NSIAD] noteType:    " + note.getCn_note_type_id());
                System.out.println("[NSIAD] noteTitle:   " + note.getCn_note_title());
                System.out.println("[NSIAD] noteBody:    " + note.getCn_note_body());
                System.out.println("[NSIAD] createTime:  " + note.getCn_note_create_time());
                System.out.println("[NSIAD] modifyTime:  " + note.getCn_note_last_modify_time());
                
                // 将Note对象保存到数据库中
                int row = noteDao.addNote(note);
                
                // 构建返回结果
                NoteResult<Note> result = new NoteResult<Note>();
                
                if (row == 1) {
                        result.setStatus(0);  // 设置状态为0，表示插入成功
                        result.setMsg("创建笔记成功");  // 设置返回消息
                        result.setData(note);  // 将Note对象附加到返回值中
                        return result;
                } else {
                        result.setStatus(1);  // 设置状态为0，表示插入失败
                        result.setMsg("创建笔记成功");  // 设置返回消息
                        return result;                        
                }
        }
}
