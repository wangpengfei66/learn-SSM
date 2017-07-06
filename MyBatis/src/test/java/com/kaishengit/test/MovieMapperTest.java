package com.kaishengit.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieExample;
import com.kaishengit.entity.MovieExample.Criteria;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.MyBatisUtil;

public class MovieMapperTest {

	private SqlSession sqlSession;
	private MovieMapper movieMapper;
	
	@Before
	public void before() {
		sqlSession = MyBatisUtil.getSqlSession();
		movieMapper = sqlSession.getMapper(MovieMapper.class);
	}
	@After
	public void after() {
		sqlSession.close();
	}
	
	@Test
	public void  findByRank() {
		/*MovieExample example = new MovieExample();
		Criteria criteria = example.createCriteria();
		criteria.andRankEqualTo(10);*/
		MovieExample example = new MovieExample();
		example.createCriteria().andRankBetween(1,100).
		andScoreGreaterThanOrEqualTo(9.0);
		
		
		List<Movie> movieList = movieMapper.selectByExample(example);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
	}
	
	@Test
	public void findByOr() {
		MovieExample example = new MovieExample();
		example.or().andScoreGreaterThan(9.0).andNameIsNotNull();
		
		List<Movie> movieList = movieMapper.selectByExample(example);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
	}
	
	//演示动态查询
	@Test
	public void search() {
		String movieName = "大";
		double minScore = 9.0;
		double maxScore = 0.0;
		MovieExample example = new MovieExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(movieName)) {
			criteria.andNameLike("%"+movieName+"%");
		}
		if(minScore > 0) {
			criteria.andScoreGreaterThan(minScore);
		}
		if(maxScore > 0) {
			criteria.andScoreLessThan(maxScore);
		}
		List<Movie> movieList = movieMapper.selectByExample(example);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
	}
	
	@Test
	public void selectByPage() {
		List<Movie> movieList = movieMapper.selectByPage(10, 10);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
	} 
	
	@Test
	public void pageHelper() {
		MovieExample example = new MovieExample();
		example.or().andScoreGreaterThan(9.0).andNameIsNotNull();
		
		//PageHelper.startPage(2,5);
		PageHelper.offsetPage(10, 10);
		List<Movie> movieList = movieMapper.selectByExample(example);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
		
		PageInfo<Movie> pageInfo = new PageInfo<Movie>(movieList);
		System.out.println("总页数" + pageInfo.getPages());
		System.out.println("总条数" + pageInfo.getTotal());
	}
	
	
}
