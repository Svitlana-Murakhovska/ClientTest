package client.controller;

import client.kafka.OrderRepository;
import com.example.client.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private final KafkaTemplate<Long, Order> kafkaTemplate;
    private final Map<Long, Order> orderMap;

    @Autowired
    public OrderController(KafkaTemplate<Long, Order> kafkaTemplate, Map<Long, Order> orderMap) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderMap = orderMap;
    }

    @PostMapping("/api/orders")
    public ResponseEntity<String> receiveOrder(@RequestBody Order order) {
        // Save the order in the map
        orderMap.put(order.getId(), order);


        // Publish the order to Kafka
        kafkaTemplate.send("order-topic", order.getId() , order);
                return ResponseEntity.ok("Success");
      ///have issue
    }

    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable Long orderId) {
        OrderRepository orderRepository = null;
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            return order.getStatus();
        } else {
            // Handle case when order is not found
            throw new RuntimeException("Order not found");
        }
}
//об map створено окремо, щоб контролер окремо зчитутався там де кафка