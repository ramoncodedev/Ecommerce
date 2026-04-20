package com.ramondev.ecommerce.service;

import com.ramondev.ecommerce.dto.request.PaymentRequest;
import com.ramondev.ecommerce.model.entity.Order;
import com.ramondev.ecommerce.model.entity.Payment;
import com.ramondev.ecommerce.model.enums.OrderStatus;
import com.ramondev.ecommerce.model.enums.PaymentStatus;
import com.ramondev.ecommerce.repository.OrderRepository;
import com.ramondev.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public Payment createPayment(PaymentRequest request) {
        if (paymentRepository.existsByOrderId(request.orderId())) {
            throw new RuntimeException("A payment already exists for order id: " + request.orderId());
        }

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + request.orderId()));

        if (order.getStatus() == OrderStatus.CANCELED) {
            throw new RuntimeException("Cannot create payment for a canceled order.");
        }

        Payment payment = Payment.builder()
                .order(order)
                .paymentMethod(request.paymentMethod())
                .status(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public Payment findByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order id: " + orderId));
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    public Payment approvePayment(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Only PENDING payments can be approved. Current status: " + payment.getStatus());
        }

        payment.setStatus(PaymentStatus.APPROVED);

        Order order = payment.getOrder();
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

    public Payment rejectPayment(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Only PENDING payments can be rejected. Current status: " + payment.getStatus());
        }

        payment.setStatus(PaymentStatus.FAILED);

        Order order = payment.getOrder();
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
