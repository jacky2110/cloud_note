package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class Advice {
        public void beforeAdvice() {
                System.out.println("[Before advice]: 在切入点之前执行");
        }
        public void afterReturningAdvice() {
                System.out.println("[After returning advice]: 在切入点正常执行完成后执行，如果连接点抛出异常，则不会执行。");
        }
        public void afterThrowingAdvice() {
                System.out.println("[After throwing advice]: 在切入点抛出异常后执行");
        }
        public void afterFinallyAdvice() {
                System.out.println("[After (finally) advice]: 在切入点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。");
        }
        
        // 注意：【环绕通知】所执行到的函数必须带一个ProceedingJoinPoint类型的参数
        // 调用该类的proceed()方法后，切入点代码才能执行，否则切入点代码将失去执行的机会
        public void aroundAdvice(ProceedingJoinPoint joinPoint) {
                System.out.println("[Around advice]: 环绕通知围绕在连接点前后，比如一个方法调用的前后。"
                                + "这是最强大的通知类型，能在方法调用前后自定义一些操作。"
                                + "环绕通知还需要负责决定是继续处理join point(调用ProceedingJoinPoint的proceed方法)还是中断执行。");
                try {
                        joinPoint.proceed();
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }
}
