package com.agrolazo.market.auth;

import java.util.UUID;

public record User(UUID id, String name, String email, String phoneNumber, Role role){


}
