package com.rajnikant.springAPI.model.sql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rajnikant.springAPI.model.NewsSource;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Indexed
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    private String headline;

    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    private String author;

    @NotBlank
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    private String title;

    @NotBlank
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
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


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "source_id")),
            @AttributeOverride(name = "name", column = @Column(name = "source_name"))})
    private NewsSource source;

} 