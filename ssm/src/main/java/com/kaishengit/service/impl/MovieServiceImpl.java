package com.kaishengit.service.impl;

import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
