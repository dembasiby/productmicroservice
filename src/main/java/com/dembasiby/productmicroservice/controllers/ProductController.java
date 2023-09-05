package com.dembasiby.productmicroservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) {
        return this.productService.getProductById(id);
    }
}
