package com.rajnikant.springAPI.controller;

import com.rajnikant.springAPI.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api")
@Api(value="Online  Portal", description="API Operations")
public class APIController {

    @ApiOperation(value = "View status of API server", response = String.class)
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public Map ping(){
        Map<String,String> response = new HashMap<>();
        response.put("Status","Success!");
        response.put("Health","Ok");
        return response;
    }

    @ApiOperation(value = "View details of of an employee", response = Employee.class)
    @RequestMapping(value = "/emp",method = RequestMethod.GET, produces = "application/json")
    public Employee emp(@RequestParam("id") int id){
        Employee response = new Employee();
        response.setName("John");
        response.setId(id);
        return response;
    }


} 