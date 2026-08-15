package com.example.demo.repository;

import com.example.demo.dto.Payment;


import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findByRequestId(String requestId);
    Payment save(Payment payment);
}

