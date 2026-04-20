package com.ramondev.ecommerce.repository;

import com.ramondev.ecommerce.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);

    List<Payment> findByStatus(com.ramondev.ecommerce.model.enums.PaymentStatus status);

}
