package dev.Marketing.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MarketingEventPublisherService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MarketingEventPublisherService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishAdvertisementEvent(AdvertisementEvent event) {
        // Temporarily use the test topic
        publish("project.release.create-topic", event.propertyId(), event);
    }

    public void publishLeadCreatedEvent(LeadCreatedEvent event) {
        // Temporarily use the test topic
        publish("project.release.create-topic", event.customerId(), event);
    }

    public void publishSurveyCompleteEvent(SurveyCompleteEvent event) {
        // Temporarily use the test topic
        publish("project.release.create-topic", event.customerId(), event);
    }

    private void publish(String topic, String key, Object event) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, key, jsonPayload);
            System.out.println("[Marketing] Published to " + topic + " : " + jsonPayload);
        } catch (Exception e) {
            System.err.println("Error serializing event: " + e.getMessage());
        }
    }
}
