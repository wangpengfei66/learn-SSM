package com.kaishengit.proxy.jdk;

import com.kaishengit.proxy.Computer;
import com.kaishengit.proxy.Lenovo;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Test {
    public static void main(String[] args) {
        //创建目标对象
        Lenovo lenovo = new Lenovo();
        //创建Invocation对象
        ComputerInvocationHandler computerInvocationHandler = new ComputerInvocationHandler(lenovo);
        //接口指向动态代理对象
        Computer computer = (Computer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),lenovo.getClass().getInterfaces(),computerInvocationHandler);
        //调用代理对象的方法
        computer.sales();
    }
}
