package com.kaishengit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2017/7/10.
 */
public class AspectAdvice {
    public void beforeAdvice() {
        System.out.println("前置通知");
    }
    public void afterResultAdvice(Object res){
        System.out.println("后置通知" + res);
    }
    public void exceptionResultAdvice(Exception e) {
        System.out.println("异常通知" + e.getMessage());
    }

    public void finallyAdvice() {
        System.out.println("最终通知");
    }
    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("before");
            joinPoint.proceed();//目标对象方法的执行
            System.out.println("around after");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常");
        }finally {
            System.out.println("最终通知");
        }
    }
}
