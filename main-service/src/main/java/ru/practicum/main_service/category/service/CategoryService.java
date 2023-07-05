package ru.practicum.main_service.category.service;

import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.dto.NewCategoryDto;
import ru.practicum.main_service.category.model.Category;


import java.util.List;

public interface CategoryService {
    CategoryDto create(NewCategoryDto newCategoryDto);

    List<CategoryDto> getAll(Integer from, Integer size);

    CategoryDto getById(Long catId);

    CategoryDto patch(Long catId, CategoryDto categoryDto);

    void deleteById(Long catId);

    Category getCategoryById(Long catId);
}