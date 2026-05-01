package dev.Marketing.producer;

public record AdvertisementEvent(
    String propertyId,
    String location,
    Double original_price,
    String promotion_detail,
    Double discount_amount,
    Double final_price,
    String start_date,
    String end_date
) {}
