package ru.valensiya.online_shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.valensiya.online_shop.model.Order;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
    }
}