package com.dembasiby.productmicroservice.controllers;

import java.util.List;

import com.dembasiby.productmicroservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO productDTO) {
        return this.productService.createProduct(productDTO);
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDTO productDetails) {
        return this.productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("{id}")
    public GenericProductDTO deleteProduct(@PathVariable("id") Long id) {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
