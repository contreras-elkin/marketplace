package com.agrolazo.market.transactions.infraestructure;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agrolazo.market.transactions.CheckoutResponse;

@Component 
public class PasarelaGateway {
    private final String wompiApiKey;
    private final String integriKey;

    public PasarelaGateway( @Value("${wompi.api-key}") String wompiApiKey, @Value("${wompi.integri-key}") String integriKey){
        this.wompiApiKey=wompiApiKey;
        this.integriKey = integriKey;
    }

    public CheckoutResponse getRecordTransaction(UUID orderId,String currency, Long amount, String reference){
        CheckoutResponse transaction= new CheckoutResponse(orderId,wompiApiKey,currency,amount, reference, getHashString(currency, amount, reference));
        return transaction;
    }

    public String getHashString(String currency, Long amount, String reference){
        String concatenatedString = reference + amount + currency + integriKey;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(concatenatedString.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
            
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
        
        
    }


}
