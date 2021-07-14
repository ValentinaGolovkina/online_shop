package ru.valensiya.online_shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
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
