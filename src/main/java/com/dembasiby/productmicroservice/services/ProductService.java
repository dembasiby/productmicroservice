package com.dembasiby.productmicroservice.services;

import java.util.List;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.exceptions.NotFoundException;

public interface ProductService {
    GenericProductDTO getProductById(String id) throws NotFoundException;

    GenericProductDTO createProduct(GenericProductDTO productDTO);

    GenericProductDTO updateProduct(String id, GenericProductDTO productDTO);

    GenericProductDTO deleteProduct(String id) throws NotFoundException;

    List<GenericProductDTO> getAllProducts();
}
