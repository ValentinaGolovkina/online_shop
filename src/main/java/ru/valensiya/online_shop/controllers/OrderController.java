package ru.valensiya.online_shop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.dto.OrderDto;
import ru.valensiya.online_shop.exceptions.InvalidAttributeValueException;
import ru.valensiya.online_shop.exceptions.ResourceNotFoundException;
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
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        if (address.isEmpty()) {
            throw new InvalidAttributeValueException("Адрес не заполнен");
        }
        if (phone.isEmpty()) {
            throw new InvalidAttributeValueException("Телефон не заполнен");
        }
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map((o) -> new OrderDto(o, orderService.findItemsByOrder(o))).collect(Collectors.toList());
    }
}
