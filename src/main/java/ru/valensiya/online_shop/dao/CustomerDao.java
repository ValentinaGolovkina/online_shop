package ru.valensiya.online_shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.valensiya.online_shop.model.Customer;
import ru.valensiya.online_shop.model.Product;

import java.util.List;

@Component
public class CustomerDao {
    private SessionFactory factory;

    public CustomerDao(ServiceFactory serviceFactory) {
        factory = serviceFactory.getFactory();
    }


    public Customer findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    public List<Customer> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    public Customer saveOrUpdate(Customer customer) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    public Customer getCustomerByIdWithProducts(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createNamedQuery("withProducts", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return customer;
        }
    }

}
