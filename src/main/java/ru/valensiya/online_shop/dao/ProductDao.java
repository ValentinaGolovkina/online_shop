package ru.valensiya.online_shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.valensiya.online_shop.model.Product;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProductDao {

    private static SessionFactory factory;

    @PostConstruct
    public void init() {
      factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
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

    public Product save(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return product;
        }
    }
}
