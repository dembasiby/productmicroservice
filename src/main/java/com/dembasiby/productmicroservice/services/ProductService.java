package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;

public interface ProductService {
    GenericProductDTO getProductById(Long id);

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
}
