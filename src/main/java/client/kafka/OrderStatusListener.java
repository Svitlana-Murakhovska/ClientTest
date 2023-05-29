package client.kafka;

import client.model.Order;
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

//    @KafkaListener(topics = "order-topic", groupId = "order-group")
//    public void listen(Order updatedOrder) {
//        // Обновление статуса заказа в базе данных
//        Order existingOrder = orderMap.get(updatedOrder.getId());
//        if (existingOrder != null) {
//            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
//        }
//    }

    @KafkaListener(topics = "notification-topic", groupId = "order-group")
    public void listenNotification(Order notification) {
        // Process the received notification
        // You can perform any actions here based on the received notification
        System.out.println("Received notification: ID=" + notification.getId() + ", Order Status=" + notification.getOrderStatus());
    }




}
