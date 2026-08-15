package com.example.demo.service;

import com.example.demo.dto.Payment;
import com.example.demo.dto.PaymentStatus;
import com.example.demo.external.ExternalPaymentClient;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Service
public class PaymentService {
    private final PaymentRepository orderRepository;
    private final ExternalPaymentClient externalPaymentClient;

    public PaymentService(PaymentRepository orderRepository, ExternalPaymentClient externalPaymentClient) {
        this.orderRepository = orderRepository;
        this.externalPaymentClient = externalPaymentClient;
    }
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        return orderRepository.findByRequestId(paymentRequest.requestId())
                .map(this::toResponse)
                .orElseGet(()->processNewPayment(paymentRequest));
    }

    private PaymentResponse processNewPayment(PaymentRequest payment) {
        Payment initialPayment = new Payment(
            payment.orderId(),
                payment.customerId(),
                payment.customerName(),
                payment.amount(),
                payment.currency(),
                payment.requestId(),
                null,
                null,
                null,
                null,
                null
        );
        ExternalPaymentClient.ExternalPaymentResult externalPaymentResult = externalPaymentClient.process(initialPayment);
        PaymentStatus paymentStatus = externalPaymentResult.status() ?PaymentStatus.SUCCESS:PaymentStatus.FAILED;
        Payment paymentResult = new Payment(
                payment.orderId(),
                payment.customerId(),
                payment.customerName(),
                payment.amount(),
                payment.currency(),
                payment.requestId(),
                Instant.now(),
                "SYSTEM",
                paymentStatus,
                externalPaymentResult.transactionId(),
                externalPaymentResult.message()
        );
        orderRepository.save(paymentResult);
        return toResponse(paymentResult);

    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(payment.orderId(),
                payment.requestId(),
                payment.amount(),
                payment.currency(),
                payment.status(),
                payment.transactionId(),
                payment.message());
    }

}

