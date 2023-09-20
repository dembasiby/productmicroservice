package com.dembasiby.productmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dembasiby.productmicroservice.models.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
