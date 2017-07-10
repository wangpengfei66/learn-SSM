package com.kaishengit.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/10.
 */
public class MyMethodInterceptor implements MethodInterceptor {
    /**
     * 指定代理行为
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("闪光。。");
        Object result = methodProxy.invokeSuper(o,objects);
        return result;
    }
}
