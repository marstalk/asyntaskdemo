package com.marstalk.asyntaskdemo.controller;


import com.marstalk.asyntaskdemo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GridController {

    @Autowired
    private CrudService crudService;

    @GetMapping("/grid/{count}")

    public String save(@PathVariable long count) {
        crudService.save();
        return "ok";
    }

}
