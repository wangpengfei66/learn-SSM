package com.kaishengit.hibernate;

import com.kaishengit.pojo.Book;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

import java.util.UUID;

public class BookTest extends BaseTest{

    @Test
    public void save() {
        Book book = new Book();
        book.setBookName("老人与海");
        session.save(book);
        String id = UUID.randomUUID().toString();//java中获取UUID的方法
    }

    @Test
    public void update() throws InterruptedException {
        //乐观锁演示
        String id = "402881995da1a16b015da1a16ed90000";
        Book book = (Book) session.get(Book.class,id);
        Thread.sleep(15000);
        book.setBookName("老人与海");
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
    }
    /*@Test
    public void update2() throws InterruptedException {
        //悲观锁演示
        String id = "402881995da1a16b015da1a16ed90000";
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class,id, LockOptions.UPGRADE);
        book.setBookName("44444");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();
                Book book1 = (Book) session1.get(Book.class,id);
                book1.setBookName("33333");
                session1.getTransaction().commit();
            }
        });
        thread.start();

        Thread.sleep(5000);
        session.getTransaction().commit();
    }*/

   /* @Test
    public void find2() {
        //演示二级缓存的例子
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
    }*/



}
