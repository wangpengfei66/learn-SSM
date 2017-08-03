package com.kaishengit;

import com.kaishengit.pojo.Customer;

public class Son extends Father<String,Customer> {

    public Son() {
        System.out.println(this);
    }
}
