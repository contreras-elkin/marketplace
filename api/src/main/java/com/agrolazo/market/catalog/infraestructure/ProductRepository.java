package com.agrolazo.market.catalog.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolazo.market.catalog.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
