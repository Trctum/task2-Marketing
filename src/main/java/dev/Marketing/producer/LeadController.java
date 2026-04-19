package dev.Marketing.producer;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeadController {

    private final LeadEventPublisherService publisherService;

    public LeadController(LeadEventPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/close")
    public String closeListing(
            @RequestParam String leadId, 
            @RequestParam String customerName, 
            @RequestParam String assignedSalesId,
            @RequestParam String timeStamp
        ) {

        LeadForwardedEvent event = new LeadForwardedEvent(leadId, customerName, assignedSalesId, timeStamp);
        publisherService.publishLeadForwardedEvent(event); 
        
        return leadId + " has been closed for Buyer: " + customerName + " and event sent to Kafka.";
    }
}