package ru.valensiya.online_shop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.dto.OrderDto;
import ru.valensiya.online_shop.exceptions.InvalidInputDataException;
import ru.valensiya.online_shop.exceptions.ResourceNotFoundException;
import ru.valensiya.online_shop.model.User;
import ru.valensiya.online_shop.services.OrderService;
import ru.valensiya.online_shop.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
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
        List<String> errors = new ArrayList<>();
        if (address.isBlank()) {
            errors.add("Field 'address' cannot be null");
        }
        if (phone.isBlank()) {
            errors.add("Field 'phone' cannot be null");
        }
        if (!errors.isEmpty()) {
            throw new InvalidInputDataException(errors);
        }
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllDtosByUsername(principal.getName());
    }
}
