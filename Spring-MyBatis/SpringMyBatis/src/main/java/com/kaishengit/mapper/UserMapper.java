package com.kaishengit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.kaishengit.entity.User;
@CacheNamespace
public interface UserMapper {
	
	User findById(Integer id);

	List<User> findAll();
	
	void save(User user);
	
	void batchsave(List<User> userList);
	
	void update(User user);

	void delById(Integer id);
	
	void delByIds(List<Integer> idList);
	
	List<User> findAllLoadDept(); 
	
	User findByUserNameAndPassword(@Param("userName") String userName,@Param("pwd") String password);

	User findByMapParams(Map<String, Object> params);
	
	List<User> searchByNameAndAddress(Map<String,Object> params);
	
}