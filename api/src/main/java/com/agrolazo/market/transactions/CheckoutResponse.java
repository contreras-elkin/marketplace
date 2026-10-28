package com.agrolazo.market.transactions;

import java.util.UUID;

public record CheckoutResponse(UUID orderId,String publicKey, String currency, Long amount, String reference, String signature) {

}
