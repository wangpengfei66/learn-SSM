package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlTest {

    public List<Account> findByList(String sql, Map<String,Object> param) {
        List<Account> accList = new ArrayList<Account>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql:///hibernate", "root", "123456");
            //这里将sql拼出来，然后进行查询，处理结果集
            //select * from account where username = :name and address = :address
            String [] strs = sql.split(" ");
            for(String s : strs) {
                if(s.matches("^:[a-zA-Z]+")) {
                    s.replace(s,"?");
                    System.out.println(s);
                }
            }
            for(int i = 0; i < strs.length; i ++) {
                sql = strs[i] + " ";
            }
            System.out.println(sql);
//            sql = sql.replaceAll("^:[a-zA-Z]+ ","? ");
//            System.out.println(sql);
            //再把sql中的问号赋值，从map中取值

            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet res = pstat.executeQuery();
            while(res.next()) {
                Account acc = new Account();
                acc.setId(res.getInt(1));
                acc.setUsername(res.getString(2));
                acc.setAddress(res.getString(3));
                acc.setAge(res.getInt(4));
                accList.add(acc);
            }
            res.close();
            pstat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accList;
    }

    @Test
    public void sqlTest() {
        String sql = "select * from account where username = :name and address = :address";
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("name","王大虎");
        param.put("address","西安");
        List<Account> accountList = findByList(sql,param);
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
    @Test
    public void mach() {
        String str = "address = :address ";
        System.out.println(str.matches(":[a-zA-Z]+\\s"));
        str = str.replaceAll(" ^:[a-zA-Z]+\\s","? ");
        System.out.println(str);

    }

}
