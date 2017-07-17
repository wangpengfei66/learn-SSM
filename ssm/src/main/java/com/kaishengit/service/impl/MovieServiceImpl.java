package com.kaishengit.service.impl;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper mapper;
    @Override
    public List<Movie> findAll() {
        return mapper.findAll();
    }

    @Override
    public void save(Movie movie) {
        mapper.save(movie);
    }

    @Override
    public void delById(Integer id) {
        mapper.delById(id);
    }

    @Override
    public Movie findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public void editMovie(Movie movie) {
        mapper.editMovie(movie);
    }

    @Override
    public PageInfo<Movie> findByParams(Integer pageNo, String title, String daoyan, Float min, Float max) {
        Map<String,Object> searchParam = new HashMap<>();
        searchParam.put("title",title);
        searchParam.put("daoyan",daoyan);
        searchParam.put("pageNum",pageNo);
        searchParam.put("min",min);
        searchParam.put("max",max);
        searchParam.put("pageSize",10);
        List<Movie> movieList = mapper.findByParam(searchParam);
        return new PageInfo<>(movieList);
    }
}
