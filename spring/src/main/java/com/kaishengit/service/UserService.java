package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.WeiXinDao;

/**
 * Created by Administrator on 2017/7/8.
 */
public class UserService {

    private UserDao userDao;
    private WeiXinDao weiXinDao;

   /* public UserService(UserDao userDao,WeiXinDao weiXinDao) {
        this.userDao = userDao;
        this.weiXinDao = weiXinDao;
    }*/
    public void save(){
        userDao.save();
       /* if(true){
            throw new RuntimeException("执行sql异常");
        }*/
    }
    public int sum() {
        return 100;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setWeiXinDao(WeiXinDao weiXinDao) {
        this.weiXinDao = weiXinDao;
    }
}
