package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.WeiXinDao;

/**
 * Created by Administrator on 2017/7/8.
 */
public class UserService {

    private UserDao userDao;
    private WeiXinDao weiXinDao;

    public UserService(UserDao userDao,WeiXinDao weiXinDao) {
        this.userDao = userDao;
        this.weiXinDao = weiXinDao;
    }
    public void save(){
        userDao.save();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setWeiXinDao(WeiXinDao weiXinDao) {
        this.weiXinDao = weiXinDao;
    }
}
