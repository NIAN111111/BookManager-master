package com.zzz.bms.web;

import com.zzz.bms.entity.BookCategory; // 修正包路径
import com.zzz.bms.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping
    public List<BookCategory> getAllCategories() {
        return bookCategoryService.getAllCategories();
    }

    @PostMapping
    public BookCategory saveCategory(@RequestBody BookCategory category) {
        return bookCategoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        bookCategoryService.deleteCategory(id);
    }
}