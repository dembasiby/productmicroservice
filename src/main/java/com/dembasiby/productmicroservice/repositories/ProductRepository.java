package com.dembasiby.productmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dembasiby.productmicroservice.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    Optional<Product> findById(UUID uuid);

    Optional<Product> findProductByTitle(String title);
    List<Product> findProductsByTitle(String title);


    @Override
    void deleteById(UUID uuid);

    @Override
    List<Product> findAll();

    List<Product> getProductsByCategory_Name(String categoryName);

}
