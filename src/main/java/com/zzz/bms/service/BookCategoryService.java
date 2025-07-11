package com.zzz.bms.service;

import com.zzz.bms.entity.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategory> getAllCategories();
    BookCategory saveCategory(BookCategory category);
    void deleteCategory(Long id);
}