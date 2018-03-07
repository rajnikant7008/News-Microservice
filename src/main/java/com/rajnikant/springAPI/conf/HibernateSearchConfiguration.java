package com.rajnikant.springAPI.conf;

import javax.persistence.EntityManager;

import com.rajnikant.springAPI.service.NewsSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HibernateSearchConfiguration {
    private final Logger LOG = LoggerFactory.getLogger(HibernateSearchConfiguration.class);

    @Autowired
    private EntityManager entityManager;

    @Bean
    NewsSearch hibernateSearchService() {
        NewsSearch newsSearch = new NewsSearch(entityManager);
        newsSearch.initializeHibernateSearch();
        return newsSearch;
    }
}