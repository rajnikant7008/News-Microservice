package com.rajnikant.springAPI.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class NewsSource implements Serializable {

    private String id;

    private String name;
} 