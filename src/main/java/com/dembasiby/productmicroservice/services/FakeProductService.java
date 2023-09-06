package com.dembasiby.productmicroservice.services;

import java.util.ArrayList;
import java.util.List;

import com.dembasiby.productmicroservice.dtos.FakeProductStoreDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;

@Service
public class FakeProductService implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String getSpecificProductUrl = "https://fakestoreapi.com/products/{id}";
    private final String getProductUrl = "https://fakestoreapi.com/products";

    public FakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductStoreDTO> response = restTemplate.getForEntity(getSpecificProductUrl, FakeProductStoreDTO.class,
                id);
        FakeProductStoreDTO genericProduct = response.getBody();

        if (genericProduct == null)
            return null;

        return getProductDetails(genericProduct);

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(
                getProductUrl, productDTO, GenericProductDTO.class);

        return response.getBody();
    }

    @Override
    public void updateProduct(Long id, GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(
                getSpecificProductUrl, productDTO, id);
    }

    @Override
    public void deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(getSpecificProductUrl, id);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductStoreDTO[]> response = restTemplate.getForEntity(
                getProductUrl, FakeProductStoreDTO[].class);
        FakeProductStoreDTO[] fakeProducts = response.getBody();

        if (fakeProducts == null) return null;

        List<GenericProductDTO> genericProducts = new ArrayList<>();
        for (FakeProductStoreDTO product : fakeProducts) {
            genericProducts.add(getProductDetails((product)));
        }
        return genericProducts;
    }

    private GenericProductDTO getProductDetails(FakeProductStoreDTO fakeProduct) {
        GenericProductDTO product = new GenericProductDTO();

        product.setId(fakeProduct.getId());
        product.setTitle(fakeProduct.getTitle());
        product.setPrice(String.valueOf(fakeProduct.getPrice()));
        product.setDescription(fakeProduct.getDescription());
        product.setCategory(fakeProduct.getCategory());
        product.setImage(fakeProduct.getImage());

        return product;
    }

}
