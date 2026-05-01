package dev.Marketing.producer;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/marketing/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final MarketingEventPublisherService publisherService;

    public CustomerController(MarketingEventPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public record LeadRequest(String name, String email, String phone, String interest) {}

    // Lead Registration
    // URL: /api/marketing/customer
    // POST / Publish Event
    @PostMapping
    public LeadCreatedEvent registerLead(@RequestBody LeadRequest request) {
        // Mock customerId
        String customerId = "CUST-" + UUID.randomUUID().toString().substring(0, 8);
        
        LeadCreatedEvent event = new LeadCreatedEvent(
            customerId,
            request.name(),
            request.email(),
            request.phone(),
            request.interest()
        );
        
        // Publish event to Kafka Topic: marketing.lead.created
        publisherService.publishLeadCreatedEvent(event);
        return event;
    }

    public record SurveyRequest(String saleId, String customerId, String feedback, String satisfiedWith, Integer rating) {}

    // Customer Survey & Insight
    // URL: /api/marketing/customer/surveys
    // POST (for saving survey)
    @PostMapping("/surveys")
    public SurveyCompleteEvent submitSurvey(@RequestBody SurveyRequest request) {
            
        // Mock score and summary based on rating
        Integer score = request.rating() * 10;
        
        // Combine satisfiedWith and feedback for the event
        String combinedFeedback = "Satisfied with: " + request.satisfiedWith() + ". Feedback: " + request.feedback();
        String summary = "Customer is " + (request.rating() >= 4 ? "highly satisfied" : "needs attention") + " with the service.";
        
        SurveyCompleteEvent event = new SurveyCompleteEvent(
            request.saleId(),
            request.customerId(),
            combinedFeedback,
            request.rating(),
            score,
            summary
        );
        
        // Publish event to Kafka Topic: marketing.survey.complete
        publisherService.publishSurveyCompleteEvent(event);
        return event;
    }
}
