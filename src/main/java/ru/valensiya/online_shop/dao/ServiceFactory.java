package ru.valensiya.online_shop.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceFactory {
    private SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

}
