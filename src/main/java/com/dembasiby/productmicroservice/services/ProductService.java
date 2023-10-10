package com.dembasiby.productmicroservice.services;

import java.util.List;

import com.dembasiby.productmicroservice.dtos.ProductDTO;
import com.dembasiby.productmicroservice.exceptions.NotFoundException;

public interface ProductService {
    ProductDTO getProductById(String id) throws NotFoundException;

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

    ProductDTO deleteProduct(String id) throws NotFoundException;

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsIn(String categoryName);
}
