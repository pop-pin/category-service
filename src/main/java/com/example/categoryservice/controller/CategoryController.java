package com.example.categoryservice.controller;

import com.example.categoryservice.entity.Category;
import com.example.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리 생성
     */
    @PostMapping("/create")
    public ResponseEntity<Long> createCategory(@RequestBody Category category) {
        Long savedCategoryId = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategoryId);
    }

    /**
     * 카테고리 목록 가져오기
     */
    @GetMapping("/all")
    public Page<Category> getCategory(@RequestParam("page") int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.getCategory(pageable);
    }

    /**
     * 카테고리 삭제
     */
    @DeleteMapping("/{category_id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable("category_id") Long id) {
        Optional<Category> savedCategory = categoryService.getCategory(id);
        if (savedCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Long deletedCategoryId = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deletedCategoryId);
    }
}

