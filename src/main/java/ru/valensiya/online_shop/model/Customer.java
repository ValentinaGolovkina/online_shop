package ru.valensiya.online_shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@NamedQuery(name="withProducts", query = "SELECT c FROM Customer c JOIN FETCH c.products WHERE c.id= :id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> products;

    public Customer(){}

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Покупатель (id: " + id + ", имя: "+ name + ")");
        for(Product p : products) {
            s.append("\n");
            s.append(p.toString());
        }
        return s.toString();
    }
}
