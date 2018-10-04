package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestShareService extends TestBase {
        private ShareService shareService;
        
        @Before
        public void init() {
                // ���ø���ķ�����ȡ������ʵ����Ȼ���ȡMapperӳ������beanʵ��
                shareService = super.getContext().getBean("id_ShareServiceImpl", ShareService.class);
        }
        
        @Test
        public void testShareNote() {
                // ����Service�㺯������ȡ�ʼ��б�
                NoteResult<Share> result = shareService.shareNote("e59ce54b-4861-4718-8fcf-90e3f84562b5");
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
        }
        
        @Test
        public void testSearchNote() {
                // ����Service�㺯���������ʼ�
                NoteResult<List<Share>> result = shareService.searchNote("����", 1);
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                
                for (Share share : result.getData()) {
                        System.out.println("===========");
                        System.out.println(share);
                }
        }
}
