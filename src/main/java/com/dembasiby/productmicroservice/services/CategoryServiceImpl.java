package com.dembasiby.productmicroservice.services;

import com.dembasiby.productmicroservice.dtos.CategoryDTO;
import com.dembasiby.productmicroservice.models.Category;
import com.dembasiby.productmicroservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO getCategory(String uuid) {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(uuid));
        if (optionalCategory.isEmpty()) return null;

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(optionalCategory.get().getUuid().toString());
        categoryDTO.setName(optionalCategory.get().getName());

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(category.getName());
            categoryDTO.setId(category.getUuid().toString());
            categoryDTO.setProducts(new ArrayList<>());
            categoryDTOS.add(categoryDTO);
        }

        return categoryDTOS;
    }
}
