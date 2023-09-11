package com.dembasiby.productmicroservice.services;

import java.util.ArrayList;
import java.util.List;

import com.dembasiby.productmicroservice.dtos.FakeProductStoreDTO;
import com.dembasiby.productmicroservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductStoreDTO> response = restTemplate.getForEntity(
                getSpecificProductUrl,
                FakeProductStoreDTO.class,
                id);
        FakeProductStoreDTO genericProduct = response.getBody();

        if (genericProduct == null)
            throw new NotFoundException("Product with id " + id  + " does not exist.");

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
    public GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<GenericProductDTO> requestEntity = new HttpEntity<>(productDTO);
        ResponseEntity<GenericProductDTO> responseEntity = restTemplate.exchange(
                getSpecificProductUrl,
                HttpMethod.PUT,
                requestEntity,
                GenericProductDTO.class,
                id);

        return responseEntity.getBody();
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDTO.class);
        ResponseExtractor<ResponseEntity<GenericProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDTO.class);
        ResponseEntity<GenericProductDTO> response = restTemplate.execute(getSpecificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (response == null) return null;

        return response.getBody();
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
