package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HqlTest {

    private Session session;
    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }
    @After
    public void after() {
        session.getTransaction().commit();
    }
    @Test
    public void selectAll() {
        String hql = "from Account where username = :name";
        Query query = session.createQuery(hql);
        query.setString("name","张广泰");
        List<Account> accountList = query.list();
        for (Account account : accountList) {
            System.out.println(account.getUsername()+ "-->" + account.getAddress());
        }
    }
   @Test
   public void count() {
       String hql = "select count(*),max(id) from Account";
       Query query = session.createQuery(hql);
       Object [] objects = (Object[]) query.uniqueResult();
       System.out.println(objects[0]);
       System.out.println(objects[1]);
   }
   @Test
   public void findByAddress() {
        String hql = "from Account where address = :address order by id desc";
        Query query = session.createQuery(hql);
        query.setParameter("address","西安");
        query.setFirstResult(0);
        query.setMaxResults(5);
        List<Account> accountList = query.list();
        for(Account account : accountList) {
            System.out.println(account);
        }
   }
}
