package com.rajnikant.springAPI.repository;

import com.rajnikant.springAPI.model.sql.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

} 