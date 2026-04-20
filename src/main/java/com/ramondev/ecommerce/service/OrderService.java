package com.ramondev.ecommerce.service;

import com.ramondev.ecommerce.dto.request.OrderItemRequest;
import com.ramondev.ecommerce.dto.request.OrderRequest;
import com.ramondev.ecommerce.mapper.OrderMapper;
import com.ramondev.ecommerce.model.entity.Order;
import com.ramondev.ecommerce.model.entity.OrderItem;
import com.ramondev.ecommerce.model.entity.Product;
import com.ramondev.ecommerce.model.entity.User;
import com.ramondev.ecommerce.repository.OrderRepository;
import com.ramondev.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order createOrder(OrderRequest request) {
        User user = new User();
        user.setId(request.userId());

        List<OrderItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemRequest.productId()));

            OrderItem item = OrderMapper.toItemEntity(itemRequest);
            item.setProduct(product);

            totalAmount = totalAmount.add(item.getUnitPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));

            items.add(item);
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(request.status());
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : items) {
            item.setOrder(savedOrder);
        }
        savedOrder.setItems(items);

        return orderRepository.save(savedOrder);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrder(Long id, OrderRequest request) {
        Order existingOrder = findById(id);
        existingOrder.setStatus(request.status());

        if (request.items() != null && !request.items().isEmpty()) {
            List<OrderItem> updatedItems = new ArrayList<>();
            BigDecimal totalAmount = BigDecimal.ZERO;

            for (OrderItemRequest itemRequest : request.items()) {
                Product product = productRepository.findById(itemRequest.productId())
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemRequest.productId()));

                OrderItem item = OrderMapper.toItemEntity(itemRequest);
                item.setProduct(product);
                item.setOrder(existingOrder);

                totalAmount = totalAmount.add(item.getUnitPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())));

                updatedItems.add(item);
            }

            existingOrder.getItems().clear();
            existingOrder.getItems().addAll(updatedItems);
            existingOrder.setTotalAmount(totalAmount);
        }

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
