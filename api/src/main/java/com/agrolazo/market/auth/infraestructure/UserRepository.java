package com.agrolazo.market.auth.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolazo.market.auth.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
