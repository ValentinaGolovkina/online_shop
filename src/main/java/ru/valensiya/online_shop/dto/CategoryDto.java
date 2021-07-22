package ru.valensiya.online_shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.valensiya.online_shop.model.Category;
import ru.valensiya.online_shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<ProductDto> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        List<ProductDto> products = new ArrayList<>();
        for (Product p : category.getProducts()) {
            products.add(new ProductDto(p));
        }
        this.products = products;
    }
}
