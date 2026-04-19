package dev.Marketing.producer;

public record LeadForwardedEvent(
    String leadId,
    String customerName,
    String phoneNumber,
    String email,
    String projectId,
    String assignedSalesId,
    String timeStamp  
) {}