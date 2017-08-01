package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NativeSqlTest {
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
    public void findAll() {
        String sql = "select id,username,address,age from account";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setFirstResult(0);
        sqlQuery.setMaxResults(5);
        List<Object[]> accountList = sqlQuery.list();
        for(Object[] array : accountList) {
            System.out.println(array[0]);
            System.out.println(array[1]);
            System.out.println(array[2]);
        }


        /*Object [] array = (Object[]) sqlQuery.uniqueResult();
        for(Object a : array) {
            System.out.println(a);
        }*/


        /*List<Account> accountList = sqlQuery.list();

        for(Account account : accountList) {
            System.out.println(account);
        }*/
    }
}
