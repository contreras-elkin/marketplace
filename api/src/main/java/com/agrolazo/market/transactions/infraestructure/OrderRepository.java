package com.agrolazo.market.transactions.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolazo.market.transactions.domain.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity,UUID>{

}
