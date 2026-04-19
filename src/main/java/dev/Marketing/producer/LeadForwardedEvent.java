package dev.Marketing.producer;

public record LeadForwardedEvent(
    String leadId,
    String customerName,
    String assignedSalesId,
    String timeStamp
) {}