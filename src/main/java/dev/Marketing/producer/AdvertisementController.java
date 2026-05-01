package dev.Marketing.producer;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/marketing/advertisements")
@CrossOrigin(origins = "*")
public class AdvertisementController {

    private final MarketingEventPublisherService publisherService;

    public AdvertisementController(MarketingEventPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/announcement")
    public AdvertisementEvent announceAdvertisement(@RequestBody AdvertisementEvent event) {
        // Data is now provided from the frontend, so we just publish it
        publisherService.publishAdvertisementEvent(event);
        return event;
    }
}
