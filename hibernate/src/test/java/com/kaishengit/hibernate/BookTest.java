package com.kaishengit.hibernate;

import com.kaishengit.pojo.Book;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.Test;

import java.util.UUID;

public class BookTest{

    /*@Test
    public void save() {
        Book book = new Book();
        book.setBookName("老人与海");
        session.save(book);
        String id = UUID.randomUUID().toString();//java中获取UUID的方法
    }

    @Test
    public void find() {
        String id = "402881995da1a16b015da1a16ed90000";
        Book book1 = (Book) session.get(Book.class,id);
        //session.clear();
        session.evict(book1);
        Book book2 = (Book) session.get(Book.class,id);
        //System.out.println(session.contains(book1));
        System.out.println(book1.getBookName());
        System.out.println(book2.getBookName());
    }*/
    @Test
    public void find2() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String id = "402881995da1a16b015da1a16ed90000";
        Book book1 = (Book) session.get(Book.class,id);
        System.out.println(book1.getBookName());
        session.getTransaction().commit();

        Cache cache = HibernateUtil.getSessionFactory().getCache();
        //cache.evictEntityRegion(Book.class);
        cache.evictEntity(Book.class,id);
        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();
        Book book2 = (Book) session1.get(Book.class,id);
        System.out.println(book1.getBookName());
        session1.getTransaction().commit();
    }



}
