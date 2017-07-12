package com.kaishengit.proxy;

/**
 * Created by Administrator on 2017/7/12.
 */
public class LenovoProxy implements Computer {
//    Lenovo lenovo = new Lenovo();
    private Computer computer;
    public LenovoProxy(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void sales() {
        System.out.println("这是台好电脑");
        computer.sales();
        System.out.println("三件套拿走");
    }
}
