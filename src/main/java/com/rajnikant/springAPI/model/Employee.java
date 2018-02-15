package com.rajnikant.springAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class Employee {

    @ApiModelProperty(notes = "The database generated employee ID")
    private int id;

    @ApiModelProperty(notes = "employee name")
    private String name;

}