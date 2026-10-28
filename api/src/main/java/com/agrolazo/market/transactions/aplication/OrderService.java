package com.agrolazo.market.transactions.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.catalog.ModuleApiCatalog;
import com.agrolazo.market.transactions.OrderRequest;
import com.agrolazo.market.transactions.domain.OrderEntity;
import com.agrolazo.market.transactions.infraestructure.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service 
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModuleApiCatalog apiCatalog;

    public OrderService(OrderRepository orderRepository,  ModuleApiCatalog apiCatalog){
        this.orderRepository=orderRepository;
        this.apiCatalog = apiCatalog;
    }

    public Order save(OrderRequest order){
        Long priceUnitProduct = apiCatalog.getReservedStock(order.quantity(),order.product_id());
        Long priceAmount= priceUnitProduct*order.quantity();
        OrderEntity orderEntity= new OrderEntity(order.product_id(), order.buyerId(), priceUnitProduct, order.quantity(), priceAmount);
        //pasar record order a OrderEntity, mapear los campos y registrar en BD
        OrderEntity orderSaved = orderRepository.save(orderEntity);
        //transactionService.save(orderEntity);
        return new Order(orderSaved.getId(), orderSaved.getProductId(), orderSaved.getBuyerId(),orderSaved.getUnitPrice(),
            orderSaved.getQuantity(), orderSaved.getAmount(),orderSaved.getStatus(), orderSaved.getCurrency());
        
    } 

    public OrderEntity getById(UUID id){
        return orderRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id)); 
    }

    
}
