package ru.valensiya.online_shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;
    /*@ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;*/
    @Override
    public String toString() {
        return "Продукт(" +
                "id:" + id +
                ", название: " + title +
                ", цена: " + price +
                ')';
    }
}
