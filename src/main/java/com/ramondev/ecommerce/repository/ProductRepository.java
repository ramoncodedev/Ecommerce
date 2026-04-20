package com.ramondev.ecommerce.repository;

import com.ramondev.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public boolean existsBySku(String sku);

}
