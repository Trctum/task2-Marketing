package dev.Marketing.producer;

public record SurveyCompleteEvent(
    String saleId,
    String customerId,
    String feedback,
    Integer rating,
    Integer score,
    String summary
) {}
