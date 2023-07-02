package org.springframework.web;

import ch.qos.logback.core.model.Model;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;


    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private void initializeCollections(Order order) {
        if (!Hibernate.isInitialized(order.getProducts())) {
            order.getProducts().size();
        }
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            initializeCollections(order);
        }
        return order;
    }

    @GetMapping
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            initializeCollections(order);
        }
        return orders;
    }

    @PostMapping
    public void create(@RequestBody Order order) {
        orderRepository.save(order);
    }
}
