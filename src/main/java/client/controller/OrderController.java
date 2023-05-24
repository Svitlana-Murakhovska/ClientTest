package client.controller;

import com.example.client.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
//об map створено окремо, щоб контролер окремо зчитутався там де кафка