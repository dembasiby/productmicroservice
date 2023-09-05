package com.dembasiby.productmicroservice.controllers;

import java.util.List;

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
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO productDTO) {
        return this.productService.createProduct(productDTO);
    }

    @PutMapping("{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDTO productDetails) {
        this.productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
