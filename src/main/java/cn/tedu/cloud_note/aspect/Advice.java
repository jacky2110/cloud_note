package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class Advice {
        public void beforeAdvice() {
                System.out.println("[Before advice]: �������֮ǰִ��");
        }
        public void afterReturningAdvice() {
                System.out.println("[After returning advice]: �����������ִ����ɺ�ִ�У�������ӵ��׳��쳣���򲻻�ִ�С�");
        }
        public void afterThrowingAdvice() {
                System.out.println("[After throwing advice]: ��������׳��쳣��ִ��");
        }
        public void afterFinallyAdvice() {
                System.out.println("[After (finally) advice]: �������ִ����ɺ�ִ�У�����������ִ����ɣ������׳��쳣������ִ�з���֪ͨ�е����ݡ�");
        }
        
        // ע�⣺������֪ͨ����ִ�е��ĺ��������һ��ProceedingJoinPoint���͵Ĳ���
        // ���ø����proceed()�����������������ִ�У������������뽫ʧȥִ�еĻ���
        public void aroundAdvice(ProceedingJoinPoint joinPoint) {
                System.out.println("[Around advice]: ����֪ͨΧ�������ӵ�ǰ�󣬱���һ���������õ�ǰ��"
                                + "������ǿ���֪ͨ���ͣ����ڷ�������ǰ���Զ���һЩ������"
                                + "����֪ͨ����Ҫ��������Ǽ�������join point(����ProceedingJoinPoint��proceed����)�����ж�ִ�С�");
                try {
                        joinPoint.proceed();
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }
}
