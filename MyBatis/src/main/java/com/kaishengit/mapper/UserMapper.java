package com.kaishengit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kaishengit.entity.User;
@CacheNamespace
public interface UserMapper {
	
	@Select("select * from t_user where id = #{id}")
	@Options(useCache=false)
	User findById(Integer id);
	@Select("select * from t_user")
	List<User> findAll();
	
	@Insert("insert into t_user (user_name,address,password)values(#{userName},#{address},#{password})")
	@Options(useGeneratedKeys=true,keyProperty="id",flushCache=FlushCachePolicy.FALSE)//禁止刷新缓存
	void save(User user);
	void batchsave(List<User> userList);
	
	@Update("update t_user set user_name = #{userName},address = #{address},password = #{password} where id = #{id}")
	void update(User user);
	@Delete("delete from t_user where id = #{id}")
	void delById(Integer id);
	void delByIds(List<Integer> idList);
	
	List<User> findAllLoadDept(); 
	@Select("select * from t_user where user_name = #{userName} and password = #{pwd}")
	User findByUserNameAndPassword(@Param("userName") String userName,@Param("pwd") String password);
	@Select("select * from t_user where user_name = #{userName} and password = #{pwd}")
	User findByMapParams(Map<String, Object> params);
	
	List<User> searchByNameAndAddress(Map<String,Object> params);
	
}
