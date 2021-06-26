package ru.valensiya.online_shop;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new RuntimeException("Продукт с id = " + id + " не найден");
    }

    public void save(Product product) {
        products.add(product);
    }

    @PostConstruct
    public void init() {
        for(int i=0;i<5;i++) {
            products.add(new Product(i,"Продукт "+i, i*47));
        }
    }
}
