package org.springframework.web;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    public Order() {
    }

    public Order(Date date, List<Product> products) {
        this.date = date;
        this.products = products;
        this.calculateTotalCost();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.calculateTotalCost();
    }

    private void calculateTotalCost() {
        if (products != null && !products.isEmpty()) {
            BigDecimal total = BigDecimal.ZERO;
            for (Product product : products) {
                total = total.add(product.getCost());
            }
            this.totalCost = total;
        }
    }
}
