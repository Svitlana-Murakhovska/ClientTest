package client.kafka;

import com.example.client.model.Order;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStatusListener {

        private final Map<Long, Order> orderMap;

        public OrderStatusListener(Map<Long, Order> orderMap) {
            this.orderMap = orderMap;
        }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void listen(Order updatedOrder) {
        // Обновление статуса заказа в базе данных
        OrderRepository orderRepository = new OrderRepository();
        Order existingOrder = orderRepository.findById(updatedOrder.getId());
        if (existingOrder != null) {
            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
            orderRepository.save(existingOrder);
        }
    }
}
