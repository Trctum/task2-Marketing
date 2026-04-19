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
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String projectId,
            @RequestParam String assignedSalesId,
            @RequestParam String timeStamp
        ) {

        LeadForwardedEvent event = new LeadForwardedEvent(leadId, customerName, phoneNumber, email, projectId, assignedSalesId, timeStamp);
        publisherService.publishLeadForwardedEvent(event); 
        
        return "Success: Lead " + leadId + " has been sent to Kafka for sale: " + customerName + phoneNumber +email + projectId + " has been Qualified and Event sent to Kafka.";
    }
}