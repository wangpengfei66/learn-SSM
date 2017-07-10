package com.kaishengit.proxy;

/**
 * Created by Administrator on 2017/7/10.
 */
public class LenovoProxy implements Computer{

    //private Lenovo lenovo = new Lenovo();
    private Computer computer;
    public LenovoProxy(Computer computer) {
        this.computer = computer;
    }
    @Override
    public void sales() {
        System.out.println("介绍配置");
        computer.sales();
        System.out.println("三件套拿走");
    }
}
