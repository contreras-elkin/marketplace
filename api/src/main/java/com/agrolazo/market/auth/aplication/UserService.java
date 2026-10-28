package com.agrolazo.market.auth.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.auth.domain.UserEntity;
import com.agrolazo.market.auth.infraestructure.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public UserEntity getById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
}
