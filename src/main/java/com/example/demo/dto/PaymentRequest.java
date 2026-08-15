package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;


public record PaymentRequest(

    @NotBlank(message="Order Id is required")
    String orderId,
    @NotBlank
    String customerId,
    String customerName,
    @NotNull
    @DecimalMin(value="0.0",inclusive = false,message="Amount must be greater than zero")
    BigDecimal amount,
    @NotBlank(message="Order Id is required")
    String currency,
    String requestId
){}
