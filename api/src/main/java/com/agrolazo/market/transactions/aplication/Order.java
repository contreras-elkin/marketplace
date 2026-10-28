package com.agrolazo.market.transactions.aplication;

import java.util.UUID;

import com.agrolazo.market.transactions.domain.enums.OrderStatus;

public record Order(UUID id, UUID product_id, UUID buyerId, Long priceUnitProduct, int quantity, Long amount, OrderStatus status, String currency) {

}
