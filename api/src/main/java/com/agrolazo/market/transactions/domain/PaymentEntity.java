package com.agrolazo.market.transactions.domain;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.agrolazo.market.transactions.domain.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payments", schema="transactions")
public class PaymentEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(name="id_order", nullable = false)
    private UUID orderId;
    @Column(nullable = false, unique = true)
    private String reference=UUID.randomUUID().toString();
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;
    // Llegan con el webhook, por eso no están en el constructor
    private String gatewayTransactionId;
    private String paymentMethodType;

    protected PaymentEntity(){}

    public PaymentEntity(UUID orderId, Long amount, String currency){
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
    }

    public UUID getId(){
        return this.id;
    }

    public UUID getOrderId(){
        return this.orderId;
    }

    public String getReference(){
        return this.reference;
    }

    public Long getAmount(){
        return this.amount;
    }

    public String getCurrency(){
        return this.currency;
    }

    public PaymentStatus getStatus(){
        return this.status;
    }

    public String getGatewayTransactionId(){
        return this.gatewayTransactionId;
    }

    public String getPaymentMethodType(){
        return this.paymentMethodType;
    }
}
