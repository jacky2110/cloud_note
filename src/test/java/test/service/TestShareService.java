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
                // 调用父类的方法获取上下文实例，然后获取Mapper映射器的bean实例
                shareService = super.getContext().getBean("id_ShareServiceImpl", ShareService.class);
        }
        
        @Test
        public void testShareNote() {
                // 调用Service层函数，获取笔记列表
                NoteResult<Share> result = shareService.shareNote("e59ce54b-4861-4718-8fcf-90e3f84562b5");
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                System.out.println(result.getData());
        }
        
        @Test
        public void testSearchNote() {
                // 调用Service层函数，搜索笔记
                NoteResult<List<Share>> result = shareService.searchNote("测试", 1);
                
                System.out.println(result.getStatus());
                System.out.println(result.getMsg());
                
                for (Share share : result.getData()) {
                        System.out.println("===========");
                        System.out.println(share);
                }
        }
}
