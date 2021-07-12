package ru.valensiya.online_shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.valensiya.online_shop.model.Customer;
import ru.valensiya.online_shop.model.Product;

import java.util.List;

@Component
public class ProductDao {

    private SessionFactory factory;

    public ProductDao(ServiceFactory serviceFactory) {
        factory = serviceFactory.getFactory();
    }

    public Product findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        }
    }

    public Product getProductByIdWithCustomers(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createNamedQuery("withCustomers", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }
}
