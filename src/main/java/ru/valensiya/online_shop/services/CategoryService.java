package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.model.Category;
import ru.valensiya.online_shop.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
