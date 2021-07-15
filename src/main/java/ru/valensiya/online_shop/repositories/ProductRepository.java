package ru.valensiya.online_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.valensiya.online_shop.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);
}
