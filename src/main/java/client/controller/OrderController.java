package client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")

public class OrderController {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final Map<Long, Order> orderMap;

    @Autowired
    public OrderController(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderMap = new HashMap<>();
    }

    @PostMapping
    public void receiveOrder(@RequestBody Order order) {
        // Save the order in the map
        orderMap.put(order.getId(), order);

        // Publish the order to Kafka
        kafkaTemplate.send("order-topic", order.getId(), order);
      ///have issue
    }
}
