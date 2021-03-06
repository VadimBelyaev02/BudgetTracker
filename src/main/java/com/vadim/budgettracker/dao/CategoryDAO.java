package com.vadim.budgettracker.dao;

import com.vadim.budgettracker.entity.Category;

import java.util.List;

public interface CategoryDAO extends CrudDAO<Category, Long> {

    List<Category> findAllByUserId(Long userId);

    Category getById(Long categoryId);

    boolean existsByName(String name);

    Category getByName(String categoryName);
}
