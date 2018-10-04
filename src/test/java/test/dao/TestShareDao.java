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
                // ����ж�������ļ������
                String[] conf = {"conf/spring-mybatis.xml", "conf/spring-mvc.xml"};
                
                // ����spring����
                ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
                
                // ��ȡMapperӳ�������ӿڣ�
                dao = ctx.getBean("shareDao", ShareDao.class);
        }
        
        @Test
        public void testShareDao() {
                // ����Share����
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
                
                // ����¼�������ݿ�
                dao.shareNote(share);
        }
        
        @Test
        public void testSearchNote() {
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keyword", "%����%");
                map.put("offset", 0);
                map.put("rows", 2);
                
                // �����ؼ��ʣ�ע�⣺����ؼ��ʲ���%�����ʾҪ��ȫƥ��
                List<Share> result = dao.findLikeTitleMap(map);
                
                System.out.println("Search done");
                
                for (Share share : result) {
                        System.out.println("===========");
                        System.out.println(share);
                }
        }
}
