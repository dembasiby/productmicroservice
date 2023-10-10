package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.ProductDTO;
import com.dembasiby.productmicroservice.dtos.PriceDTO;
import com.dembasiby.productmicroservice.models.Category;
import com.dembasiby.productmicroservice.models.Price;
import com.dembasiby.productmicroservice.models.Product;
import com.dembasiby.productmicroservice.repositories.CategoryRepository;
import com.dembasiby.productmicroservice.repositories.PriceRepository;
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
    private final PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository,
                                  PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public ProductDTO getProductById(String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        return product.map(this::getProductDTODetailsFromProduct).orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(productDTO.getCategory());
        Category category;

        if (optionalCategory.isEmpty()) {
            category = new Category();
            category.setName(productDTO.getCategory());
            category.setUuid(UUID.randomUUID());
            categoryRepository.save(category);
        } else category = optionalCategory.get();

        Price price = new Price(productDTO.getPriceDTO().getPrice(),
                productDTO.getPriceDTO().getCurrency());
        price.setUuid(UUID.randomUUID());
        priceRepository.save(price);

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
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));

        if (optionalProduct.isEmpty()) return null;

        Product product = optionalProduct.get();

        product.getPrice().setPrice(productDTO.getPriceDTO().getPrice());
        product.getPrice().setCurrency(productDTO.getPriceDTO().getCurrency());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        productRepository.save(product);

        return getProductDTODetailsFromProduct(product);

    }

    @Override
    public ProductDTO deleteProduct(String id) {
        ProductDTO toBeDeleted = this.getProductById(id);
        productRepository.deleteById(UUID.fromString(id));
        return toBeDeleted;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> genericProductDTOS = new ArrayList<>();

        for (Product product : products)
            genericProductDTOS.add(getProductDTODetailsFromProduct(product));

        return genericProductDTOS;
    }

    @Override
    public List<ProductDTO> getAllProductsIn(String categoryName) {
        List<Product> products = this.productRepository.getProductsByCategory_Name(categoryName);
        List<ProductDTO> genericProductDTOS = new ArrayList<>();

        for (Product product : products) {
            genericProductDTOS.add(getProductDTODetailsFromProduct(product));
        }
        return genericProductDTOS;
    }

    private ProductDTO getProductDTODetailsFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getUuid().toString());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getImage());
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(UUID.randomUUID().toString());
        priceDTO.setPrice(product.getPrice().getPrice());
        priceDTO.setCurrency(product.getPrice().getCurrency());
        productDTO.setPriceDTO(priceDTO);

        return productDTO;
    }
}
