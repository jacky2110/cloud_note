package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestShareDao {
        
        private ShareDao dao;
        
        @Before
        public void init() {
                // 针对有多个配置文件的情况
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml"};
                
                // 启动spring容器
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                // 获取Mapper映射器（接口）
                dao = ctx.getBean("shareDao", ShareDao.class);
        }
        
        @Test
        public void testShareDao() {
                // 创建Share对象
                Share share = new Share();
                
                String shareId = NoteUtil.createId();
                String shareTitle = "TestShareDao.shareTitle";
                String shareBody = "TestShareDao.shareBody";
                String noteId = "TestShareDao.noteId001";
                
                share.setCn_share_id(shareId);
                share.setCn_share_title(shareTitle);
                share.setCn_share_body(shareBody);
                share.setCn_note_id(noteId);
                
                System.out.println(share.getCn_share_id());
                System.out.println(share.getCn_share_title());
                System.out.println(share.getCn_share_body());
                System.out.println(share.getCn_note_id());
                
                // 将记录插入数据库
                dao.shareNote(share);
        }
        
        @Test
        public void testSearchNote() {
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keyword", "%测试%");
                map.put("offset", 0);
                map.put("rows", 2);
                
                // 搜索关键词，注意：如果关键词不加%号则表示要完全匹配
                List<Share> result = dao.findLikeTitleMap(map);
                
                System.out.println("Search done");
                
                for (Share share : result) {
                        System.out.println("===========");
                        System.out.println(share);
                }
        }
}
