package dev.Marketing.producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LeadEventPublisherService {
    // เปลี่ยนจาก Object เป็น String
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "marketing.lead.events";

    public LeadEventPublisherService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishLeadForwardedEvent(LeadForwardedEvent event) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(event);
            
            kafkaTemplate.send(TOPIC, event.leadId(), jsonPayload);
            System.out.println("[Marketing] Success : " + jsonPayload);
        } catch (Exception e) {
            System.err.println("Error serializing event: " + e.getMessage());
        }
    }
}