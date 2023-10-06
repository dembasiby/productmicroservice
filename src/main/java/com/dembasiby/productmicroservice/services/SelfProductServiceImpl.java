package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.GenericProductDTO;
import com.dembasiby.productmicroservice.models.Category;
import com.dembasiby.productmicroservice.models.Price;
import com.dembasiby.productmicroservice.models.Product;
import com.dembasiby.productmicroservice.repositories.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDTO getProductById(String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        return product.map(this::getProductDTODetailsFromProduct).orElse(null);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productDTO) {
        Product product = new Product();
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(productDTO.getCategory());
        Category category;

        if (optionalCategory.isEmpty()) {
            category = new Category();
            category.setName(productDTO.getCategory());
            category.setUuid(UUID.randomUUID());
            categoryRepository.save(category);
        } else category = optionalCategory.get();

        Price price = new Price(Double.parseDouble(productDTO.getPrice()), "");

        product.setUuid(UUID.randomUUID());
        product.setTitle(productDTO.getTitle());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category);
        product.setPrice(price);

        productRepository.save(product);
        productDTO.setId(product.getUuid().toString());

        return productDTO;
    }

    @Override
    @Transactional
    public GenericProductDTO updateProduct(String id, GenericProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));

        if (optionalProduct.isEmpty()) return null;

        Product product = optionalProduct.get();

        Price price = product.getPrice();
        if (price.getPrice() != Double.parseDouble(productDTO.getPrice())) {
            price.setPrice(Double.parseDouble(productDTO.getPrice()));
        }

        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(price);
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

    @Override
    public List<GenericProductDTO> getAllProductsIn(String categoryName) {
        List<Product> products = this.productRepository.getProductsByCategory_Name(categoryName);
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for (Product product : products) {
            genericProductDTOS.add(getProductDTODetailsFromProduct(product));
        }
        return genericProductDTOS;
    }

    private GenericProductDTO getProductDTODetailsFromProduct(Product product) {
        GenericProductDTO productDTO = new GenericProductDTO();

        productDTO.setId(product.getUuid().toString());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getImage());
        productDTO.setPrice(String.valueOf(product.getPrice().getPrice()));

        return productDTO;
    }
}
