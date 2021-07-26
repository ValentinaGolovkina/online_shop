package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.exceptions.ResourceNotFoundException;
import ru.valensiya.online_shop.services.OrderService;
import ru.valensiya.online_shop.services.ProductService;
import ru.valensiya.online_shop.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (!cart.add(productId)) {
            cart.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId)));
        }
    }

    @GetMapping("/inc/{productId}")
    public void inc(@PathVariable Long productId) {
        cart.changeQuantity(productId, 1);
    }
    @GetMapping("/dec/{productId}")
    public void dec(@PathVariable Long productId) {
        cart.changeQuantity(productId, -1);
    }

    @GetMapping("/order")
    public Integer order() {
        return orderService.order();
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        if (!cart.deleteProductById(productId)) {
            throw new ResourceNotFoundException("Unable delete product from cart. Product not found id: " + productId);
        }
    }

    @DeleteMapping
    public void clearCart() {
        cart.clear();
    }

}
