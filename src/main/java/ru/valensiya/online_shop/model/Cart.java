package ru.valensiya.online_shop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getTitle() + " добавлен в корзину");
    }
    public void deleteProduct(Product product) {
        products.remove(product);
        System.out.println(product.getTitle() + " удален из корзины");
    }
}
