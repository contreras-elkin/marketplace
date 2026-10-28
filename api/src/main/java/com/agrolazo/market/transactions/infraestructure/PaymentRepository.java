package com.agrolazo.market.transactions.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolazo.market.transactions.domain.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID>{

    
}
