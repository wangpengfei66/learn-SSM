package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Test {

    @org.junit.Test
    public void first() {
        //读取配置文件
        Configuration configuration = new Configuration().configure();
        //创建sessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
        //开启事务
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Account account = new Account();
        account.setUsername("王琦");
        account.setAddress("西安");
        account.setAge(78);
        session.save(account);
        //提交事务
        transaction.commit();
    }
    @org.junit.Test
    public void query() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Account account = (Account) session.get(Account.class,1);
        System.out.println(account.getUsername());
        session.getTransaction().commit();
    }
    @org.junit.Test
    public void update() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Account account = (Account) session.get(Account.class,1);
        account.setUsername("王洪");
        session.getTransaction().commit();
    }
    @org.junit.Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Account account = (Account) session.get(Account.class,1);
        session.delete(account);
        session.getTransaction().commit();
    }
    @org.junit.Test
    public void selectAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "from Account where username = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"张广泰");
        List<Account> accountList = query.list();
        for (Account account : accountList) {
            System.out.println(account.getUsername()+ "-->" + account.getAddress());
        }
        session.getTransaction().commit();
    }




}
