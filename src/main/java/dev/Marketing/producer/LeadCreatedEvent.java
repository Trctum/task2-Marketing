package dev.Marketing.producer;

public record LeadCreatedEvent(
    String customerId,
    String name,
    String email,
    String phone,
    String interest
) {}
