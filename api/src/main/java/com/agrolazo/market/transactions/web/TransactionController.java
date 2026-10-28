package com.agrolazo.market.transactions.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrolazo.market.transactions.OrderRequest;
import com.agrolazo.market.transactions.CheckoutResponse;
import com.agrolazo.market.transactions.aplication.CheckoutService;


@RestController 
@RequestMapping("/api/orders")
public class TransactionController {
    private CheckoutService checkoutService;

    public TransactionController(CheckoutService checkoutService){
        this.checkoutService= checkoutService;
    }

    @PostMapping
    public ResponseEntity<CheckoutResponse> create(@RequestBody OrderRequest order){
        CheckoutResponse response = checkoutService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
