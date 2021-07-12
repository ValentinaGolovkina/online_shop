package ru.valensiya.online_shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.valensiya.online_shop.services.CustomerService;
import ru.valensiya.online_shop.services.ProductService;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        ProductService productService = context.getBean(ProductService.class);

        System.out.println(customerService.getCustomerByIdWithProducts(1L));
        System.out.println(productService.getProductByIdWithCustomers(1L));
    }
}
