package ru.valensiya.online_shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQuery(name="withCustomers", query = "SELECT p FROM Product p JOIN FETCH p.customers WHERE p.id= :id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;
    @ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public Product(){}

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Продукт(" +
                "id:" + id +
                ", название: " + title +
                ", цена: " + price +
                ')';
    }
}
