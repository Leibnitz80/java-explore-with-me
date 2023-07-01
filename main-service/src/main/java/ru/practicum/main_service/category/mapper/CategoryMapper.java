package ru.practicum.main_service.category.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.dto.NewCategoryDto;
import ru.practicum.main_service.category.model.Category;

@UtilityClass
public class CategoryMapper {
    public static Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto) {
        Category result = new Category();
        result.setName(newCategoryDto.getName());
        return result;
    }

    public static Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category result = new Category();
        result.setId(categoryDto.getId());
        result.setName(categoryDto.getName());
        return result;
    }

    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto result = new CategoryDto();
        result.setId(category.getId());
        result.setName(category.getName());
        return result;
    }
}