package ru.valensiya.online_shop.services;

import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.dao.CustomerDao;
import ru.valensiya.online_shop.model.Customer;
import ru.valensiya.online_shop.model.Product;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomerByIdWithProducts(Long id) {
        return customerDao.getCustomerByIdWithProducts(id);
    }

    public void saveOrUpdateCustomer(String title) {
        Customer customer = new Customer(null, title);
        customerDao.saveOrUpdate(customer);
    }
}
