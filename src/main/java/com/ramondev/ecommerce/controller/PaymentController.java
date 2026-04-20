package com.ramondev.ecommerce.controller;

import com.ramondev.ecommerce.dto.request.PaymentRequest;
import com.ramondev.ecommerce.dto.response.PaymentResponse;
import com.ramondev.ecommerce.mapper.PaymentMapper;
import com.ramondev.ecommerce.model.entity.Payment;
import com.ramondev.ecommerce.model.enums.PaymentStatus;
import com.ramondev.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.createPayment(request);
        return ResponseEntity.ok(PaymentMapper.toResponse(payment));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        List<Payment> payments = paymentService.findAll();
        List<PaymentResponse> response = payments.stream()
                .map(PaymentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return ResponseEntity.ok(PaymentMapper.toResponse(payment));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrder(@PathVariable Long orderId) {
        Payment payment = paymentService.findByOrderId(orderId);
        return ResponseEntity.ok(PaymentMapper.toResponse(payment));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        List<Payment> payments = paymentService.findByStatus(status);
        List<PaymentResponse> response = payments.stream()
                .map(PaymentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<PaymentResponse> approvePayment(@PathVariable Long id) {
        Payment payment = paymentService.approvePayment(id);
        return ResponseEntity.ok(PaymentMapper.toResponse(payment));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<PaymentResponse> rejectPayment(@PathVariable Long id) {
        Payment payment = paymentService.rejectPayment(id);
        return ResponseEntity.ok(PaymentMapper.toResponse(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
