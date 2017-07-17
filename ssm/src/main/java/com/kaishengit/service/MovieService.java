package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface MovieService {
    List<Movie> findAll();

    void save(Movie movie);

    void delById(Integer id);

    Movie findById(Integer id);

    void editMovie(Movie movie);

    PageInfo<Movie> findByParams(Integer pageNo, String title, String daoyan, Float min, Float max);
}
