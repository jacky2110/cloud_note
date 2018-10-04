package cn.tedu.cloud_note.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//在spring的配置文件中使用了<context:component-scan>启动了组件扫描后，
//在类的定义前添加 @Controller @Component @Repository @Service这4个注释之一，
//即可以将该类交给spring容器管理，也就是说不再需要在配置文件（spring-mvc.xml）中为该方法创建bean元素了
//4个注解的功能玩去一样，但不同的名称可以提醒我们使用该注解的类的用途
//由于这里是切面代码，不属于Controller或Service等层，因此使用了@Component
@Component
@Aspect  // 标记该类是切面的组成部分
public class ExceptionBean {

        // 指定切入点为【抛出异常后】
        // ex是被抛出的异常对象
        @AfterThrowing(pointcut="within(cn.tedu.cloud_note.service..*)", throwing="ex")
        public void execute(Exception ex) {
                
                System.out.println("ExceptionBean.execute()=============");
                
                try {
                        // 将异常信息写入文件
                        // 参1：指定文件路径
                        // 参2：true表示以追加的形式写文件
                        FileWriter fw = new FileWriter("D:\\temp\\note_error.log", true);
                        PrintWriter pw = new PrintWriter(fw);
                        
                        // 获取当前时间
                        Date time = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timeStr = sdf.format(time);
                        
                        // 向文件中写入日志的抬头信息
                        pw.println("******************************************");
                        pw.println("* 异常类型: " + ex);
                        pw.println("* 异常事件: " + timeStr);
                        pw.println("****************异常详细信息****************");
                        // 将异常的详细信息写入日志中
                        ex.printStackTrace(pw);
                        
                        fw.close();
                        pw.close();
                        
                } catch (Exception e) {
                        // e.printStackTrace();
                        System.out.println("记录日志失败");
                }
        }
}
