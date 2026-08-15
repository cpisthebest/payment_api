package com.example.demo.external;

import com.example.demo.dto.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
@Component
public class MockExternalPaymentClient implements ExternalPaymentClient {

    @Override
    public ExternalPaymentResult process(Payment payment) {
        boolean success = ThreadLocalRandom.current().nextInt(100)<99;
        if(success) {
            return new ExternalPaymentResult(true,
                    UUID.randomUUID().toString(),
                    "Payment processed successfully");
        }
        return new ExternalPaymentResult(false,
                null,
                "External payment declined");
    }
}
