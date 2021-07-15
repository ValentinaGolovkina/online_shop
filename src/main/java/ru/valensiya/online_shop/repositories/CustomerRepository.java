package ru.valensiya.online_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.valensiya.online_shop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
