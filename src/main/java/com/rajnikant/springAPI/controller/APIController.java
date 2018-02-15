package com.rajnikant.springAPI.controller;

import com.rajnikant.springAPI.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class APIController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public String ping(){
        String response = "Hello There!";
        return response;
    }

    @RequestMapping(value = "/emp",method = RequestMethod.GET, produces = "application/json")
    public Employee emp(@RequestParam("id") int id){
        Employee response = new Employee();
        response.setName("John");
        response.setId(id);
        return response;
    }


} 