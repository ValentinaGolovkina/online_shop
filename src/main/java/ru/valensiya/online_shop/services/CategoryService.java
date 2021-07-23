package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.model.Category;
import ru.valensiya.online_shop.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category findByTitle(String categoryTitle) {
        return categoryRepository.findByTitle(categoryTitle);
    }
}
