package com.agrolazo.market.transactions.aplication;

import org.springframework.stereotype.Service;

import com.agrolazo.market.transactions.OrderRequest;
import com.agrolazo.market.transactions.CheckoutResponse;
import com.agrolazo.market.transactions.infraestructure.PasarelaGateway;

import jakarta.transaction.Transactional;

@Service 
public class CheckoutService {
    private final PasarelaGateway pasarelaGateway;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public CheckoutService(PasarelaGateway pasarelaGateway, PaymentService paymentService, OrderService orderService){
        this.pasarelaGateway = pasarelaGateway;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @Transactional 
    public CheckoutResponse create(OrderRequest orderRequest){
        Order orderCreate = orderService.save(orderRequest);
        Payment payment = paymentService.save(orderCreate.id(), orderCreate.amount(), orderCreate.currency());
        CheckoutResponse transaction = pasarelaGateway.getRecordTransaction(orderCreate.id(),payment.currency(), payment.amount(), payment.reference());

        return transaction;
    }




}
