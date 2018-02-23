package com.rajnikant.springAPI.model.sql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rajnikant.springAPI.model.NewsSource;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String headline;

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String url;

    private String urlToImage;

    private Date publishedAt;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    //private NewsSource newsSource;

} 