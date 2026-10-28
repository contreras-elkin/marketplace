package com.agrolazo.market.catalog.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.catalog.ModuleApiCatalog;
import com.agrolazo.market.catalog.Product;
import com.agrolazo.market.catalog.domain.ProductEntity;

@Service 
public class ApiCatalogImple implements ModuleApiCatalog{
     
    private final ProductService productService;

    public ApiCatalogImple(ProductService productService){
        this.productService = productService;
    }

    @Override
    public Product getProduct(UUID id) {
        ProductEntity product = productService.getById(id);
        
        return new Product(product.getId(), product.getStock(), product.getPriceUnit());
    }

    @Override
    public Long getReservedStock(int reservation, UUID id) {
        ProductEntity product = productService.deductStock(reservation, id);
        return  product.getPriceUnit();
    }


   
    

}
