package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.valensiya.online_shop.dto.OrderDto;
import ru.valensiya.online_shop.dto.OrderItemDto;
import ru.valensiya.online_shop.exceptions.ResourceNotFoundException;
import ru.valensiya.online_shop.model.Order;
import ru.valensiya.online_shop.model.OrderItem;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.model.User;
import ru.valensiya.online_shop.repositories.OrderItemRepository;
import ru.valensiya.online_shop.repositories.OrderRepository;
import ru.valensiya.online_shop.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final Cart cart;

    @Transactional
    public void createOrder(User user, String address, String phone) {
        Order order = new Order();
        order.setPrice(cart.getPrice());
        order.setItems(new ArrayList<>());
        order.setUser(user);
        order.setPhone(phone);
        order.setAddress(address);
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
    }

    @Transactional
    public List<OrderDto> findAllDtosByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderItem> findItemsByOrder(Order order) {
        return orderItemRepository.findOrderItemsByOrder(order);
    }
}
