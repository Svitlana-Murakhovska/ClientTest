package client.kafka;

import org.springframework.beans.factory.annotation.Autowired;

public class KafkaTemplate {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String msg, String s) {
        String topicName = null;
        kafkaTemplate.sendMessage(null, msg);
    }
}
