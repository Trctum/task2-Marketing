package dev.Marketing.producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
            System.out.println("Simulating send auto event.. (Commented out to prevent unknown topic error)");
            /*
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            List<LeadForwardedEvent> events = List.of(
                new LeadForwardedEvent("LD001", "Ratchata","0812345678","ratchata@gmail.com","PRJ001", "SALE001", timeStamp),
                new LeadForwardedEvent("LD002", "Suradit", "0812345679","suradit@gmail.com","PRJ001", "SALE002", timeStamp),
                new LeadForwardedEvent("LD003", "Anchana", "0812345680","anchana@gmail.com","PRJ002", "SALE003", timeStamp)
            );
            
            events.forEach(producerService::publishLeadForwardedEvent);
            */
        };
    }
}
