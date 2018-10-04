package cn.tedu.cloud_note.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������������룬������Controller��Service�Ȳ㣬���ʹ����@Component
@Component
@Aspect  // ��Ǹ������������ɲ���
public class ExceptionBean {

        // ָ�������Ϊ���׳��쳣��
        // ex�Ǳ��׳����쳣����
        @AfterThrowing(pointcut="within(cn.tedu.cloud_note.service..*)", throwing="ex")
        public void execute(Exception ex) {
                
                System.out.println("ExceptionBean.execute()=============");
                
                try {
                        // ���쳣��Ϣд���ļ�
                        // ��1��ָ���ļ�·��
                        // ��2��true��ʾ��׷�ӵ���ʽд�ļ�
                        FileWriter fw = new FileWriter("D:\\temp\\note_error.log", true);
                        PrintWriter pw = new PrintWriter(fw);
                        
                        // ��ȡ��ǰʱ��
                        Date time = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timeStr = sdf.format(time);
                        
                        // ���ļ���д����־��̧ͷ��Ϣ
                        pw.println("******************************************");
                        pw.println("* �쳣����: " + ex);
                        pw.println("* �쳣�¼�: " + timeStr);
                        pw.println("****************�쳣��ϸ��Ϣ****************");
                        // ���쳣����ϸ��Ϣд����־��
                        ex.printStackTrace(pw);
                        
                        fw.close();
                        pw.close();
                        
                } catch (Exception e) {
                        // e.printStackTrace();
                        System.out.println("��¼��־ʧ��");
                }
        }
}
