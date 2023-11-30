package com.poppin.categoryservice.service;

import com.poppin.categoryservice.entity.Category;
import com.poppin.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }
    @Transactional
    public Long saveCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    public Page<Category> getCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional
    public Long deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return id;
    }
}
