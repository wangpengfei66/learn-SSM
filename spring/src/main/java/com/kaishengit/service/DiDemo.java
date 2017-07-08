package com.kaishengit.service;

import com.kaishengit.dao.UserDao;

import java.util.*;

/**
 * Created by Administrator on 2017/7/8.
 */
public class DiDemo {

    private double score;
    private String name;
    private List<String> nameList;
    private Set<String> mySet;
    private Map<String,UserDao> userMap;
    private Properties config;

    public void show() {
        System.out.println("score" + score);
        System.out.println("name" + name);
        for(String name : nameList) {
            System.out.println("name:" + name);
        }
        for(String set : mySet){
            System.out.println("mySet" + set);
        }
        for (Map.Entry<String,UserDao> entry : userMap.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        Enumeration keys = config.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) config.get(key);
            System.out.println(key + "-->" + value);
        }
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setUserMap(Map<String, UserDao> userMap) {
        this.userMap = userMap;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }
}
