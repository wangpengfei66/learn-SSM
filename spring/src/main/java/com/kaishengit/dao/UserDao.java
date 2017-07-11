package com.kaishengit.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

/**
 * Created by Administrator on 2017/7/7.
 */
@Named
//@Lazy
//@Scope("protoType")
public class UserDao {
   /* public UserDao() {
        System.out.println("对象创建");
    }*/

    public void save() {
        System.out.println("userDaoo create");
    }
    public void init(){
        System.out.println("init");
    }
    public void destroy() {
        System.out.println("destroy");
    }
}
