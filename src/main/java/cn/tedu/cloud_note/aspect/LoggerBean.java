package cn.tedu.cloud_note.aspect;

public class LoggerBean {
        
        public void byExecution() {
                System.out.println("ʹ��execution����AOP����ע��");
        }
        
        public void byWithin() {
                System.out.println("ʹ��within����AOP����ע��");
        }
        
        public void byBean() {
                System.out.println("ʹ��bean����AOP����ע��");
        }
        
}
