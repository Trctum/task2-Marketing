package dev.Marketing.producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(LeadEventPublisherService producerService) {
        return args -> {
            System.out.println("Simulating send auto event..");
            
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LeadForwardedEvent autoEvent = new LeadForwardedEvent("LD001", "Ratchata", "SALE001", timeStamp);
            
            producerService.publishLeadForwardedEvent(autoEvent);
        };
    }

}
