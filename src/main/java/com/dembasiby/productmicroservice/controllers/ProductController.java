package com.dembasiby.productmicroservice.controllers;

import java.util.List;

import com.dembasiby.productmicroservice.exceptions.NotFoundException;
import com.dembasiby.productmicroservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(@Qualifier("SelfProductServiceImpl") ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO productDTO) {
        return this.productService.createProduct(productDTO);
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProduct(@PathVariable("id") String id, @RequestBody GenericProductDTO productDetails) {
        return this.productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("{id}")
    public GenericProductDTO deleteProduct(@PathVariable("id") String id) throws NotFoundException {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") String id) throws NotFoundException {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }

    // Get all categories
    @GetMapping("/categories")
    public List<String> getAllCategoryNames() {
        return this.categoryService.getAllCategoryNames();
    }

    // Get products in a specific category
    @GetMapping("/category/{categoryName}")
    public List<GenericProductDTO> getAllProductsIn(@PathVariable("categoryName") String categoryName) {
        return this.productService.getAllProductsIn(categoryName);
    }
}
