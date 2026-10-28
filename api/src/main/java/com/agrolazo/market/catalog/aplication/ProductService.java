package com.agrolazo.market.catalog.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.catalog.domain.ProductEntity;
import com.agrolazo.market.catalog.infraestructure.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service 
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    public ProductEntity getById(UUID id){
        ProductEntity product = productRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        return product;
    }

    public void save(ProductEntity product){
        productRepository.save(product);
    }

    public ProductEntity deductStock(int reservation, UUID id){
        ProductEntity product = getById(id);
        if(reservation<=product.getStock()){
            product.setStock(product.getStock()-reservation);
            save(product);
        }else{
            throw new RuntimeException("Insufficient stock");
        }
        
        return product;
        
    }
}
