package com.dembasiby.productmicroservice.services;

import org.springframework.stereotype.Service;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;


@Service
public class FakeProductService implements ProductService {

    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return null;
    }

    @Override
    public void updateProduct(GenericProductDTO genericProductDTO) {
        //TODO
    }

    @Override
    public void deleteProduct(GenericProductDTO genericProductDTO) {
        // TODO
    }

}
