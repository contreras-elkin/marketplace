package com.agrolazo.market.catalog;

import java.util.UUID;


public interface ModuleApiCatalog {
    Product getProduct(UUID id);
    Long getReservedStock(int reservation, UUID id);
   
    
}
