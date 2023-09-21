package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.models.Product;
import com.dembasiby.productmicroservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDTO getProductById(String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        return product.map(this::getProductDTODetailsFromProduct).orElse(null);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productDTO) {
        Product product = new Product();

        product.setUuid(UUID.randomUUID());
        product.setTitle(productDTO.getTitle());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(Double.parseDouble(productDTO.getPrice()));

        productRepository.save(product);

        return productDTO;
    }

    @Override
    @Transactional
    public GenericProductDTO updateProduct(String id, GenericProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));

        if (optionalProduct.isEmpty()) return null;

        Product product = optionalProduct.get();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(Double.parseDouble(productDTO.getPrice()));
        product.setImage(productDTO.getImage());
        productRepository.save(product);

        return getProductDTODetailsFromProduct(product);

    }

    @Override
    public GenericProductDTO deleteProduct(String id) {
        GenericProductDTO toBeDeleted = this.getProductById(id);
        productRepository.deleteById(UUID.fromString(id));
        return toBeDeleted;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for (Product product : products)
            genericProductDTOS.add(getProductDTODetailsFromProduct(product));

        return genericProductDTOS;
    }

    private GenericProductDTO getProductDTODetailsFromProduct(Product product) {
        GenericProductDTO productDTO = new GenericProductDTO();

        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setPrice(String.valueOf(product.getPrice()));

        return productDTO;
    }
}
