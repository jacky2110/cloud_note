package cn.tedu.cloud_note.aspect;

public class LoggerBean {
        
        public void byExecution() {
                System.out.println("使用execution进行AOP功能注入");
        }
        
        public void byWithin() {
                System.out.println("使用within进行AOP功能注入");
        }
        
        public void byBean() {
                System.out.println("使用bean进行AOP功能注入");
        }
        
}
