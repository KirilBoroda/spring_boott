package org.springframework.web;

import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        LOGGER.info("Fetching order with ID: {}", id);
        return orderRepository.findById(id).orElse(null);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        LOGGER.info("Fetching all orders");
        return orderRepository.findAll();
    }

    @PostMapping("/")
    public void addOrder(@RequestBody Order order) {
        LOGGER.info("Adding a new order: {}", order);
        orderRepository.save(order);
    }
}
