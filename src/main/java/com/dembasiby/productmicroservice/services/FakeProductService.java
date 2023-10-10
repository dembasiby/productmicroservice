package com.dembasiby.productmicroservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dembasiby.productmicroservice.dtos.FakeProductStoreDTO;
import com.dembasiby.productmicroservice.dtos.PriceDTO;
import com.dembasiby.productmicroservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.dembasiby.productmicroservice.dtos.ProductDTO;

@Service
public class FakeProductService implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String getSpecificProductUrl = "https://fakestoreapi.com/products/{id}";
    private final String getProductUrl = "https://fakestoreapi.com/products";

    public FakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductDTO getProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductStoreDTO> response = restTemplate.getForEntity(
                getSpecificProductUrl,
                FakeProductStoreDTO.class,
                UUID.fromString(id));
        FakeProductStoreDTO genericProduct = response.getBody();

        if (genericProduct == null)
            throw new NotFoundException("Product with id " + id  + " does not exist.");

        return getProductDTODetails(genericProduct);

    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(
                getProductUrl, productDTO, ProductDTO.class);

        return response.getBody();
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(productDTO);
        ResponseEntity<ProductDTO> responseEntity = restTemplate.exchange(
                getSpecificProductUrl,
                HttpMethod.PUT,
                requestEntity,
                ProductDTO.class,
                UUID.fromString(id));

        return responseEntity.getBody();
    }

    @Override
    public ProductDTO deleteProduct(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(ProductDTO.class);
        ResponseExtractor<ResponseEntity<ProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(ProductDTO.class);
        ResponseEntity<ProductDTO> response = restTemplate.execute(getSpecificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (response == null) return null;

        return response.getBody();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductStoreDTO[]> response = restTemplate.getForEntity(
                getProductUrl, FakeProductStoreDTO[].class);
        FakeProductStoreDTO[] fakeProducts = response.getBody();

        if (fakeProducts == null) return null;

        List<ProductDTO> genericProducts = new ArrayList<>();
        for (FakeProductStoreDTO product : fakeProducts) {
            genericProducts.add(getProductDTODetails((product)));
        }
        return genericProducts;
    }

    @Override
    public List<ProductDTO> getAllProductsIn(String categoryName) {
        return null;
    }

    private ProductDTO getProductDTODetails(FakeProductStoreDTO fakeProduct) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(fakeProduct.getId().toString());
        productDTO.setTitle(fakeProduct.getTitle());
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(UUID.randomUUID().toString());
        priceDTO.setPrice(fakeProduct.getPrice());
        priceDTO.setCurrency("");
        productDTO.setDescription(fakeProduct.getDescription());
        productDTO.setCategory(fakeProduct.getCategory());
        productDTO.setImage(fakeProduct.getImage());

        return productDTO;
    }

}
