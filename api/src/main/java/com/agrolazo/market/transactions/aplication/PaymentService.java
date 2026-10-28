package com.agrolazo.market.transactions.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.transactions.domain.PaymentEntity;
import com.agrolazo.market.transactions.infraestructure.PaymentRepository;


@Service 
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
            
    }

    public Payment save(UUID orderId, Long orderAmount, String orderCurrency){
        PaymentEntity payment= new PaymentEntity(orderId, orderAmount, orderCurrency);
        payment= paymentRepository.save(payment);
        
        return new Payment(payment.getCurrency(), payment.getAmount(), payment.getReference());
    }

    

        
        


}
