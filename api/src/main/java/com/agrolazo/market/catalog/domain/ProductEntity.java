package com.agrolazo.market.catalog.domain;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.agrolazo.market.catalog.ProductUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="products",schema="catalog")
public class ProductEntity{
    @Id 
    @GeneratedValue 
    @UuidGenerator 
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductUnit unit;

    @Column(nullable = false)
    private Long price_unit;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, length = 100)
    private String municipality;

    private UUID id_producer;

    protected  ProductEntity(){

    }
    public ProductEntity(int stock){
        this.stock=stock;
    
    }

    public UUID getId(){
        return this.id;
    }
    public int getStock(){
        return this.stock;
    }
    public int setStock(int stock){
        return this.stock=stock;
    }
    public Long getPriceUnit(){
        return this.price_unit;
    }

}
