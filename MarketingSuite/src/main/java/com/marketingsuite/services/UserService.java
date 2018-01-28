package com.marketingsuite.services;

import com.marketingsuite.entities.User;
import com.marketingsuite.models.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegistrationModel registrationModel);
    User findByUsername(String username);
    User findByEmail(String email);
}
