package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valensiya.online_shop.dto.CategoryDto;
import ru.valensiya.online_shop.services.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return new CategoryDto(categoryService.findById(id));
    }
}
