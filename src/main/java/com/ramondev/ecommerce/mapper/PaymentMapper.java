package com.ramondev.ecommerce.mapper;

import com.ramondev.ecommerce.dto.response.PaymentResponse;
import com.ramondev.ecommerce.model.entity.Payment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrder() != null ? payment.getOrder().getId() : null)
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
