package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dao.CategoryDao;
import com.example.quizwebapplication.domain.Category;
import com.example.quizwebapplication.dto.category.CategoryDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public List<CategoryDTO> getAllCategoryNames() {
        return categoryDao.getAllCategoryNames();
    }

    @Transactional
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }
}
