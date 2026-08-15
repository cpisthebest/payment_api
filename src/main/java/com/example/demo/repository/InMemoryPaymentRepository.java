package com.example.demo.repository;

import com.example.demo.dto.Payment;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class InMemoryPaymentRepository implements PaymentRepository{
    private final ConcurrentHashMap<String,Payment> payments = new ConcurrentHashMap<>();
    @Override
    public Optional<Payment> findByRequestId(String requestId) {
        return Optional.ofNullable(payments.get(requestId));
    }

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.requestId(),payment);
        return payment;
    }
}
