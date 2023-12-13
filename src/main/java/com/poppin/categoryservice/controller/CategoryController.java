package com.poppin.categoryservice.controller;

import com.poppin.categoryservice.entity.Category;
import com.poppin.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 생성
    @PostMapping("")
    public ResponseEntity<Long> createCategory(@RequestParam("category_name")String categoryName) {
        Category newCategory = Category.builder()
                .name(categoryName)
                .build();
        Long savedCategoryId = categoryService.saveCategory(newCategory);
        return ResponseEntity.ok(savedCategoryId);
    }

    //카테고리 목록 전부 불러오기
    @GetMapping("/all")
    public Page<Category> getCategory(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.getCategory(pageable);
    }

    //카테고리 삭제
    @DeleteMapping("")
    public ResponseEntity<Long> deleteCategory(@RequestParam("category_id") Long id) {
        Optional<Category> savedCategory = categoryService.getCategory(id);
        if (savedCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Long deletedCategoryId = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deletedCategoryId);
    }
}

