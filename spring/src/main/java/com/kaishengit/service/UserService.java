package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.WeiXinDao;
import com.kaishengit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/7/8.
 */
@Service
public class UserService {
   @Autowired
   private UserDao userDao;
   public User findById(int id) {
       return userDao.findById(id);
   }

   public void save(User user){
       userDao.save(user);
   }

}
