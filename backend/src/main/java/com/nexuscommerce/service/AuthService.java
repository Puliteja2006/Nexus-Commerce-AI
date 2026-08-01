package com.nexuscommerce.service;

import com.nexuscommerce.dto.auth.AuthResponse;
import com.nexuscommerce.dto.auth.LoginRequest;
import com.nexuscommerce.dto.auth.RegisterRequest;
import com.nexuscommerce.dto.auth.UserDto;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    UserDto getCurrentUser(String email);
}
