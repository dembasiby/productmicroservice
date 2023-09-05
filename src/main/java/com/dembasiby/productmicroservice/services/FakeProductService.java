package com.dembasiby.productmicroservice.services;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;

@Service
public class FakeProductService implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";

    public FakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.getForEntity(getProductUrl, GenericProductDTO.class,
                id);
        GenericProductDTO genericProduct = response.getBody();

        if (genericProduct == null)
            return null;

        GenericProductDTO product = new GenericProductDTO();
        product.setId(genericProduct.getId());
        product.setTitle(genericProduct.getTitle());
        product.setPrice(genericProduct.getPrice());
        product.setDescription(genericProduct.getDescription());
        product.setCategory(genericProduct.getCategory());
        product.setImage(genericProduct.getImage());

        return product;

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(
                createProductUrl, productDTO, GenericProductDTO.class);

        return response.getBody();
    }

    @Override
    public void updateProduct(Long id, GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(
                getProductUrl, productDTO, id);
    }

    @Override
    public void deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(getProductUrl, id);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", List.class);
        List<GenericProductDTO> genericProducts = response.getBody();
        return genericProducts;
    }

}
