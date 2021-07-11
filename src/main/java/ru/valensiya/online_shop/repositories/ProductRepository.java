package ru.valensiya.online_shop.repositories;

import org.springframework.stereotype.Component;
import ru.valensiya.online_shop.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new RuntimeException("Продукт с id = " + id + " не найден");
    }

    public void save(Product product) {
        product.setId(products.stream().mapToLong(Product::getId).max().getAsLong() + 1L);
        products.add(product);
    }

    @PostConstruct
    public void init() {
        for(int i=0;i<5;i++) {
            products.add(new Product((long) i,"Продукт "+i, i*47));
        }
    }
}
