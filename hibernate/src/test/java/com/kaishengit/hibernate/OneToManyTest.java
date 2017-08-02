package com.kaishengit.hibernate;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyTest extends BaseTest {
    @Test
    public void save() {
        User user = (User) session.get(User.class,2);
        Address address = new Address();
        address.setAddress("王城巩义");
        address.setCityName("洛阳");
        /*User user = new User();
        user.setUserName("王晓明");*/
        address.setUser(user);
        //先保存一，再保存多
        session.save(user);
        session.save(address);
    }
    @Test
    public void delete() {
        User user = (User) session.get(User.class,5);
        //删除用户 级联删除  删除部门 和裁除部门注意
        session.delete(user);
    }


    @Test
    public void save2() {
        Address address = new Address();
        address.setAddress("长垣县红利达到");
        address.setCityName("洛阳");
        User user = new User();
        user.setUserName("刘江");
        address.setUser(user);

        Set<Address> addressSet = new HashSet<Address>();
        addressSet.add(address);
        user.setAddressSet(addressSet);
        //先保存一，再保存多
        session.save(user);
        session.save(address);
    }


    @Test
    public void findByAddressId() {
        //通过user_id外键查询某个用户拥有的地址 !!!!!!
        Address address = (Address) session.get(Address.class,1);
        System.out.println(address.getAddress() + "-->" + address.getCityName());
        address.getUser().getAddressSet();
        //延迟加载，如果需要一，则再次发出请求进行查询
        //Hibernate.initialize(address.getUser());
        //System.out.println(address.getUser().getId());
    }
    @Test
    public void findAddressByUserId() {
        //通过user_id外键来查询某个用户拥有的地址  !!!!
        Criteria criteria = session.createCriteria(Address.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.userName","何辅堂"));
        //criteria.add(Restrictions.eq("user.id",2));
        List<Address> addressList = criteria.list();
        for(Address address : addressList) {
            System.out.println(address.getAddress());
        }
    }


    @Test
    public void findByUserId() {
        //查询一个用户，加载用户所对应的地址
        User user = (User) session.get(User.class,1);
        System.out.println(user.getUserName());
        //延迟加载
        Set<Address> addressSet = user.getAddressSet();
        for(Address address : addressSet) {
            System.out.println(address.getId() +"-->"+address.getAddress());
        }
    }

}
