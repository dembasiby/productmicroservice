package com.dembasiby.productmicroservice.services;

import java.util.List;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;

public interface ProductService {
    GenericProductDTO getProductById(Long id);

    GenericProductDTO createProduct(GenericProductDTO productDTO);

    void updateProduct(Long id, GenericProductDTO productDTO);

    void deleteProduct(Long id);

    List<GenericProductDTO> getAllProducts();
}
