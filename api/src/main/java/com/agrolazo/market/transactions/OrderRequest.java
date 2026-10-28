package com.agrolazo.market.transactions;

import java.util.UUID;

public record OrderRequest(UUID product_id, UUID buyerId,int quantity) {

}
