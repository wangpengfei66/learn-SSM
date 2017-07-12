package com.kaishengit.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;

/**
 * Created by Administrator on 2017/7/12.
 */
public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //设置目标对象(父类)
        enhancer.setSuperclass(Mouse.class);
        //设置代理行为
        enhancer.setCallback(new MyMethodInterceptor());
        Mouse mouse = (Mouse) enhancer.create();
        mouse.move();
    }
}
