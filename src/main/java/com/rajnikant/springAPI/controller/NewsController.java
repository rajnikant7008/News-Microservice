package com.rajnikant.springAPI.controller;

import com.rajnikant.springAPI.model.Employee;
import com.rajnikant.springAPI.model.sql.News;
import com.rajnikant.springAPI.repository.NewsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("v1/")
@Api(description="APIs for news service")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @ApiOperation(value = "View status of API server")
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity ping(){
        return ResponseEntity.ok().body("Success!");
    }


    @ApiOperation(value = "Get All the news")
    @RequestMapping(value = "/news",method = RequestMethod.GET, produces = "application/json")
    public Page<News> getAllNews(Pageable pageable){
        return newsRepository.findAll(pageable);
    }

    @ApiOperation(value = "Get the news from news id")
    @RequestMapping(value = "/news/{id}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<News> getNewsById(@PathVariable(value = "id") Long newsId){
        News news = newsRepository.findOne(newsId);
        if(news == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(news);
    }

    @ApiOperation(value = "Add news")
    @RequestMapping(value = "/news",method = RequestMethod.POST, produces = "application/json")
    public News addNews(@Valid @RequestBody News news){
        return newsRepository.save(news);
    }

    @ApiOperation(value = "Delete news by id")
    @RequestMapping(value = "/news/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<News> deleteNews(@PathVariable(value = "id") Long newsId){
        News news = newsRepository.findOne(newsId);
        if(news == null) {
            return ResponseEntity.notFound().build();
        }

        newsRepository.delete(news);
        return ResponseEntity.ok().build();
    }

    // Update a News
    @ApiOperation(value = "Update news by id")
    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long newsId,
                                           @Valid @RequestBody News newsDetails) {
        News news = newsRepository.findOne(newsId);
        if(news == null) {
            return ResponseEntity.notFound().build();
        }
        news.setTitle(newsDetails.getTitle());
        news.setAuthor(newsDetails.getAuthor());
        News updatedNews = newsRepository.save(news);
        return ResponseEntity.ok(updatedNews);
    }
} 