package com.kaishengit.dao;


import com.kaishengit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "insert into t_user(user_name,password,address,dept_id) values(?,?,?,?)";
        jdbcTemplate.update(sql,user.getUser_name(),user.getPassword(),user.getAddress(),user.getDept_id());
    }
    public void delete(int id) {
        String sql = "delete from t_user where id = ?";
        jdbcTemplate.update(sql,id);
    }
    public User findById(int id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(),id);
    }
    public List<User> findAll() {
        String sql = "select * from t_user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    public User findByAddress (String address) {
        String sql = "select * from t_user where address = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),address);
    }
    public int count() {
        String sql = "select count(*) from t_user";
        return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>()).intValue();
    }

    private class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUser_name(resultSet.getString("user_name"));
            user.setAddress(resultSet.getString("address"));
            user.setPassword(resultSet.getString("password"));
            user.setDept_id(resultSet.getInt("dept_id"));
            user.setId(resultSet.getInt("id"));
            return user;
        }
    }


}
