package ru.valensiya.online_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.valensiya.online_shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
