package com.kaishengit.proxy;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Client {
    public static void main(String[] args) {
        Lenovo lenovo = new Lenovo();
        Dell dell = new Dell();
        LenovoProxy lenovoProxy = new LenovoProxy(dell);
        lenovoProxy.sales();
    }
}
