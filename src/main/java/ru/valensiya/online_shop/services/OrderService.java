package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.dto.OrderItemDto;
import ru.valensiya.online_shop.model.Order;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.OrderRepository;
import ru.valensiya.online_shop.utils.Cart;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final Cart cart;

    public Integer order() {
        Random random = new Random();
        Integer number = Math.abs(random.nextInt());
        for(OrderItemDto oi : cart.getItems()) {
            Order order = new Order();
            order.setPrice(oi.getPrice());
            Product product = productService.findById(oi.getProductId()).get();
            order.setProduct(product);
            order.setQuantity(oi.getQuantity());
            order.setNumber(number);
            orderRepository.save(order);
        }
        return number;
    }
}
