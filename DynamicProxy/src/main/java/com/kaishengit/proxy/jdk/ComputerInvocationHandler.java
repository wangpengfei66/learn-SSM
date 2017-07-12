package com.kaishengit.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/12.
 */
public class ComputerInvocationHandler implements InvocationHandler {

    //指定代理对象，可以传入一切代理对象，代理对象是以接口的形式表示
    private Object target;
    public ComputerInvocationHandler(Object object) {
        this.target = object;
    }

    /**
     * 指定代理行为
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        Object res = method.invoke(target,args);
        return res;
    }
}
