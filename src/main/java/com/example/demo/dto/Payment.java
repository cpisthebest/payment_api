package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record Payment(
    String orderId,
    String customerId,
    String customerName,
    BigDecimal amount,
    String currency,
    String requestId,
    Instant createdAt,
    String createdBy,
    PaymentStatus status,
    String transactionId,
    String message
){}
