package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getCategory(String uuid);
    List<CategoryDTO> getAllCategories();

    List<String> getAllCategoryNames();
}
