package com.example.demo.external;

import com.example.demo.dto.Payment;

public interface ExternalPaymentClient {
    ExternalPaymentResult process(Payment payment);
    record ExternalPaymentResult(
            boolean status,
            String transactionId,
            String message
    ){}
}
