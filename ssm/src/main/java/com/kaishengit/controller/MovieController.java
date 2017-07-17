package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public String list(@RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo,
                       @RequestParam(required = false) String title,
                       @RequestParam(required = false) String daoyan,
                       @RequestParam(required = false) Float min,
                       @RequestParam(required = false) Float max,
                       Model model) {
        title = StringUtils.isoToUtf(title);
        daoyan = StringUtils.isoToUtf(daoyan);
        //model.addAttribute("movieList",movieService.findAll());
        PageInfo<Movie> pageInfo = movieService.findByParams(pageNo,title,daoyan,min,max);
        model.addAttribute("page",pageInfo);
        model.addAttribute("title",title);
        model.addAttribute("daoyan",daoyan);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        return "movie/list";
    }

    @GetMapping("/add")
    public String addMovie() {
        return "movie/add";
    }

    @PostMapping("/add")
    public String saveMovie(Movie movie, RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/movie";
    }

    @GetMapping("/del/{id:\\d+}")
    public String delMovie(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        movieService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/movie";
    }
    @GetMapping("/edit/{id:\\d+}")
    public String editMovie(@PathVariable Integer id, Model model) {
        //根据id查找movie
        Movie movie = movieService.findById(id);
        //把对象传入JSP
        model.addAttribute("movie",movie);
        return "movie/edit";
    }
    @PostMapping("/edit")
    public String editMovie(Movie movie,RedirectAttributes redirectAttributes) {
        movieService.editMovie(movie);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/movie";
    }

}
