package ru.valensiya.online_shop.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.valensiya.online_shop.dto.OrderItemDto;
import ru.valensiya.online_shop.model.Order;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.OrderRepository;
import ru.valensiya.online_shop.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public void clear() {
        items.clear();
        price = BigDecimal.ZERO;
    }

    public boolean add(Long productId) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    private void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto oid : items) {
            price = price.add(oid.getPrice());
        }
    }

    public boolean deleteProductById(Long productId) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(productId)) {
                items.remove(o);
                return true;
            }
        }
        return false;
    }

    public void changeQuantity(Long productId, int i) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(i);
            }
        }
    }
}
