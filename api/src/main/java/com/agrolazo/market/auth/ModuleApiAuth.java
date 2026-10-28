package com.agrolazo.market.auth;

import java.util.UUID;


public interface ModuleApiAuth {
    User getUser(UUID id);

}
