package com.dembasiby.productmicroservice.controllers;

import com.dembasiby.productmicroservice.dtos.CategoryDTO;
import com.dembasiby.productmicroservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{uuid}")
    public CategoryDTO getCategory(@PathVariable("uuid") String uuid) {
        return categoryService.getCategory(uuid);
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {
        return this.categoryService.getAllCategories();
    }
}
