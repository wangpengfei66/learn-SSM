package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CriteriaTest {

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
    public void findAll () {
        Criteria criteria = session.createCriteria(Account.class);
        List<Account> accountList = criteria.list();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
    @Test
    public void findByUserName() {
        Criteria criteria = session.createCriteria(Account.class);
        //criteria.add(Restrictions.eq("username","王大虎"));
        //criteria.add(Restrictions.ge("id",7));
        //criteria.add(Restrictions.in("id",new Integer [] {2,3,4}));
        //criteria.add(Restrictions.like("username","老", MatchMode.ANYWHERE));
        //criteria.add(Restrictions.or(Restrictions.eq("username","谢广坤"),Restrictions.eq("username","刘能")));
        //使用disjunction连接的查询条件，之间也是使用or连接   username = 王老五 or address = 北京
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("username","谢广坤"));
        disjunction.add(Restrictions.eq("username","刘能"));
        criteria.add(disjunction);
        List<Account> accountList = criteria.list();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
    @Test
    public void page() {
        Criteria criteria = session.createCriteria(Account.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(5);
        //排序
        criteria.addOrder(Order.desc("id"));

        List<Account> accountList = criteria.list();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
    @Test
    public void count() {
        Criteria criteria = session.createCriteria(Account.class);
        //criteria.setProjection(Projections.count("age"));
        //criteria.setProjection(Projections.rowCount());
        //criteria.setProjection(Projections.countDistinct("age"));
        //criteria.setProjection(Projections.max("id"));
        //setProjection方法只能设置一个聚合查询条件，如果需要多个则通过ProjectionList对象来设置
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.max("id"));
        projectionList.add(Projections.rowCount());
        criteria.setProjection(projectionList);
        //Integer count = (Integer) criteria.uniqueResult();
        Object [] array = (Object[]) criteria.uniqueResult();
        System.out.println(array[0]);
        System.out.println(array[1]);
        //System.out.println(count);
    }

}
