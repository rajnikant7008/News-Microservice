package com.rajnikant.springAPI.controller;

import com.rajnikant.springAPI.model.sql.News;
import com.rajnikant.springAPI.repository.NewsRepository;
import com.rajnikant.springAPI.service.NewsSearch;
import com.rajnikant.springAPI.utils.NullAwareBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


@RestController
@RequestMapping("v1/")
@Api(description = "APIs for news service")
public class NewsController {

    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsSearch newsSearch;

    private static final BeanUtilsBean beanUtils = new NullAwareBeanUtils();

    @ApiOperation(value = "View status of Server")
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity ping() {
        return ResponseEntity.ok().body("Success!");
    }

    @ApiOperation(value = "Get All the news")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/news", method = RequestMethod.GET, produces = "application/json")
    public Page<News> getAllNews(Pageable pageable) {
        LOG.info("Fetching all news, page : {} page size : {} ", pageable.getPageNumber(), pageable.getPageSize());
        return newsRepository.findAll(pageable);
    }

    @ApiOperation(value = "Get news by id")
    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<News> getNewsById(@PathVariable(value = "id") Long newsId) {
        News news = newsRepository.findOne(newsId);
        LOG.info("Fetching news by Id : {}", newsId);
        if (news == null) {
            LOG.info("News not found for Id : {}", newsId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(news);
    }

    @ApiOperation(value = "Add news")
    @RequestMapping(value = "/news", method = RequestMethod.POST, produces = "application/json")
    public News addNews(@Valid @RequestBody News news) {
        News response = newsRepository.save(news);
        LOG.info("Created News with Id: {} and title : {}", response.getId(), response.getTitle());
        return response;
    }

    @ApiOperation(value = "Delete news by id")
    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<News> deleteNews(@PathVariable(value = "id") Long newsId) {
        News news = newsRepository.findOne(newsId);
        if (news == null) {
            LOG.info("News not found for Id : {}", newsId);
            return ResponseEntity.notFound().build();
        }
        LOG.info("Deleted News with Id : {}", newsId);
        newsRepository.delete(news);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update news by id")
    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long newsId,
                                           @Valid @RequestBody News newsDetails) throws InvocationTargetException, IllegalAccessException {
        News news = newsRepository.findOne(newsId);
        if (news == null) {
            return ResponseEntity.notFound().build();
        }
        beanUtils.copyProperties(news, newsDetails);
        news.setId(newsId);
        LOG.info("Updating News for Id : {}", newsId);
        News updatedNews = newsRepository.save(news);
        return ResponseEntity.ok(updatedNews);
    }

    @ApiOperation(value = "Search the news")
    @RequestMapping(value = "/news/search/{query}", method = RequestMethod.GET, produces = "application/json")
    public List<News> searchNews(@PathVariable(value = "query") String query) {
        LOG.info("Searching News for keyword : {} ", query);
        return newsSearch.fuzzySearch(query);
    }
} 