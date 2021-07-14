package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.model.Customer;
import ru.valensiya.online_shop.repositories.CustomerRepository;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer saveCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customerRepository.save(customer);
    }
}
