package client.kafka;

import com.example.client.model.Order;
import com.example.client.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusListener {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderStatusListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void listen(Order updatedOrder) {
        // Обновление статуса заказа в базе данных
        Order existingOrder = orderRepository.findById(updatedOrder.getId());
        if (existingOrder != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
            orderRepository.save(existingOrder);
        }
    }
}
