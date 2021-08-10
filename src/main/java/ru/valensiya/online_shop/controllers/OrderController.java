package ru.valensiya.online_shop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valensiya.online_shop.dto.OrderDto;
import ru.valensiya.online_shop.model.User;
import ru.valensiya.online_shop.services.OrderService;
import ru.valensiya.online_shop.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        System.out.println(user.getEmail());
        orderService.createOrder();
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
