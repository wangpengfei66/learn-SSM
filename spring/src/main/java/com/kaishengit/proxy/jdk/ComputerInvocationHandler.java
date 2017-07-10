package com.kaishengit.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/10.
 */
public class ComputerInvocationHandler implements InvocationHandler {

    //指定目标对象
    private Object target;
    public ComputerInvocationHandler (Object target) {
        this.target = target;
    }


    /**
     * 指定代理行为
     * @param proxy
     * @param method 目标对象的方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("嘉姐200");
        Object result = method.invoke(target,args);
        return result;
    }
}
