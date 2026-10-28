package com.agrolazo.market.transactions.domain;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.agrolazo.market.transactions.domain.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="orders", schema="transactions")
public class OrderEntity {
    @Id 
    @GeneratedValue 
    @UuidGenerator 
    private UUID id;
    @Column(nullable = false)
    private UUID productId;
    @Column(nullable = false)
    private UUID buyerId;
    @Column(nullable = false)
    private Long unitPrice;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private String currency = "COP";
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status= OrderStatus.PENDING;

    protected  OrderEntity(){}

    public OrderEntity(UUID productId, UUID buyerId, Long unitPrice, int quantity, Long amount){
        this.productId=productId;
        this.buyerId=buyerId;
        this.unitPrice=unitPrice;
        this.quantity= quantity;
        this.amount=amount;
    }

    public UUID getId(){
        return this.id;
    } 

    public UUID getProductId(){
        return this.productId;
    }

    public UUID getBuyerId(){
        return this.buyerId;
    }

    public Long getUnitPrice(){
        return this.unitPrice;
    }

    public int getQuantity(){
        return this.quantity;
    } 


    public Long getAmount(){
        return this.amount;
    }

    public String getCurrency(){
        return this.currency;
    }

    public OrderStatus getStatus(){
        return this.status;
    }


}
