package com.example.demo.dto;

import java.math.BigDecimal;

public record PaymentResponse(String orderId,
                              String requestId,
                              BigDecimal amount,
                              String currency,
                              PaymentStatus status,
                              String transactionId,
                              String message) {
}
