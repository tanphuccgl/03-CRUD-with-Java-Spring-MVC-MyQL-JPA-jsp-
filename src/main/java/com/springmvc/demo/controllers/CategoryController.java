package com.springmvc.demo.controllers;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "categories")
//http:localhost:8080/categories
public class CategoryController {
    @Autowired //Inject "categoryRepository" - Dependency Injection
    private CategoryRepository categoryRepository;
    //return name of "jsp file"
    //http:localhost:8083/categories
    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
/*
 * 
 * @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
 */
