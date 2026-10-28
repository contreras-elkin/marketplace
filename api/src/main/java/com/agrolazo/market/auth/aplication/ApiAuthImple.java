package com.agrolazo.market.auth.aplication;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agrolazo.market.auth.ModuleApiAuth;
import com.agrolazo.market.auth.User;
import com.agrolazo.market.auth.domain.UserEntity;

@Service
public class ApiAuthImple implements ModuleApiAuth{

    private final UserService userService;

    public ApiAuthImple(UserService userService){
        this.userService = userService;
    }
    @Override
    public User getUser(UUID id) {
        UserEntity user = userService.getById(id);

        return new User(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRole());
    }

}
