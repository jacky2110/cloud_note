package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.RelationDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;
import test.TestBase;

public class TestRelationDao extends TestBase {
        private RelationDao rdao;
        
        @Before
        public void init() {
                rdao = super.getContext().getBean("relationDao", RelationDao.class);
        }
        
        @Test
        public void test() {
                User user = rdao.findUserAndBooks("03590914-a934-4da9-ba4d-b41799f917d1");
                
                System.out.println("==========�û���Ϣ==========");
                System.out.println("���֣�" + user.getCn_user_name());
                System.out.println("�ǳƣ�" + user.getCn_user_nick());
                System.out.println("�ʼǱ�������" + user.getBooks().size());
                System.out.println("=========�ʼǱ��б�=========");
                
                for (Book b : user.getBooks()) {
                        System.out.println(b.getCn_notebook_name());
                }
        }
}
