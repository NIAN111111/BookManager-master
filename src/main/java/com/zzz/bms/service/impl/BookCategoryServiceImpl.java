package com.zzz.bms.service.impl;

import com.zzz.bms.entity.BookCategory;
import com.zzz.bms.repository.BookCategoryRepository;
import com.zzz.bms.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 确保类定义正确，没有语法错误
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategory> getAllCategories() {
        return bookCategoryRepository.findAll();
    }

    @Override
    public BookCategory saveCategory(BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        bookCategoryRepository.deleteById(id);
    }
}