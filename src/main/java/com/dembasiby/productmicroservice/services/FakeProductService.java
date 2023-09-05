package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;


public class FakeProductService implements ProductService {

    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return null;
    }

}
