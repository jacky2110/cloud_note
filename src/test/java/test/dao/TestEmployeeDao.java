package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.EmployeeDao;
import cn.tedu.cloud_note.entity.Employee;
import test.TestBase;

public class TestEmployeeDao extends TestBase {
        private EmployeeDao dao;
        
        @Before
        public void init() {
                dao = super.getContext().getBean("employeeDao", EmployeeDao.class);
        }
        
        @Test
        public void test() {
                Employee emp = new Employee();
                emp.setName("zhangfei");
                emp.setAge(88);
                dao.save(emp);
                System.out.println(emp);
        }
}
