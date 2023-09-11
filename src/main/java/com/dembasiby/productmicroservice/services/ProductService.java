package com.dembasiby.productmicroservice.services;

import java.util.List;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    GenericProductDTO getProductById(Long id) throws NotFoundException;

    GenericProductDTO createProduct(GenericProductDTO productDTO);

    GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO);

    GenericProductDTO deleteProduct(Long id);

    List<GenericProductDTO> getAllProducts();
}
