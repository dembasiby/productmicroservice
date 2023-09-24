package com.dembasiby.productmicroservice.repositories;

import com.dembasiby.productmicroservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Override
    List<Category> findAll();

    Optional<Category> findByName(String category);
    Optional<Category> findCategoryByName(String categoryName);

    @Override
    Optional<Category> findById(UUID uuid);
}
