package com.kaishengit.proxy.jdk;

import com.kaishengit.proxy.Computer;
import com.kaishengit.proxy.Lenovo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/12.
 */
public class ComputerInvocationHandlerTest {
    public static void main(String[] args) {
        //创建目标对象
        Lenovo lenovo = new Lenovo();
        //Handler对象
        ComputerInvocationHandler computerInvocationHandler = new ComputerInvocationHandler(lenovo);

        //接口指向实现类
        Computer computer = (Computer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),lenovo.getClass().getInterfaces(),computerInvocationHandler);
        computer.sales();
    }
}
