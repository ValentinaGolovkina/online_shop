package ru.valensiya.online_shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.valensiya.online_shop.model.Order;
import ru.valensiya.online_shop.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String address;
    private String phone;
    private BigDecimal price;
    private List<OrderItemDto> items;

    public OrderDto(Order order, List<OrderItem> items) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getPrice();
        this.items = items.stream().map((i)->new OrderItemDto(i.getProduct())).collect(Collectors.toList());;
    }
}