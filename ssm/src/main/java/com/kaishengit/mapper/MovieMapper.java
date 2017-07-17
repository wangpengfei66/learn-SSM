package com.kaishengit.mapper;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface MovieMapper {
    List<Movie> findAll();
    void save(Movie movie);

    void delById(Integer id);

    Movie findById(Integer id);

    void editMovie(Movie movie);

    List<Movie> findByParam(Map<String, Object> searchParam);
}
