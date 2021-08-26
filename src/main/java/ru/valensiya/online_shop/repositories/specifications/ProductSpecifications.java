package ru.valensiya.online_shop.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.valensiya.online_shop.model.Product;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(BigDecimal minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
}
