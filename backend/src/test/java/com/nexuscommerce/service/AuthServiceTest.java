package com.nexuscommerce.service;

import com.nexuscommerce.common.exception.ApiException;
import com.nexuscommerce.dto.auth.AuthResponse;
import com.nexuscommerce.dto.auth.LoginRequest;
import com.nexuscommerce.dto.auth.RegisterRequest;
import com.nexuscommerce.entity.Role;
import com.nexuscommerce.entity.User;
import com.nexuscommerce.repository.UserRepository;
import com.nexuscommerce.security.JwtTokenProvider;
import com.nexuscommerce.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User testUser;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("Password@123")
                .build();

        loginRequest = LoginRequest.builder()
                .email("john.doe@example.com")
                .password("Password@123")
                .build();

        testUser = User.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .role(Role.ROLE_CUSTOMER)
                .active(true)
                .build();
    }

    @Test
    @DisplayName("Should successfully register a new user")
    void shouldRegisterNewUserSuccessfully() {
        when(userRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(passwordEncoder.encode("Password@123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("mockJwtToken");

        AuthResponse response = authService.register(registerRequest);

        assertNotNull(response);
        assertEquals("john.doe@example.com", response.getEmail());
        assertEquals("mockJwtToken", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw ApiException when registering duplicate email")
    void shouldThrowExceptionWhenRegisteringDuplicateEmail() {
        when(userRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        assertThrows(ApiException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Should successfully authenticate user and return JWT token")
    void shouldAuthenticateUserSuccessfully() {
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn("mockJwtToken");
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(testUser));

        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("mockJwtToken", response.getToken());
        assertEquals("john.doe@example.com", response.getEmail());
    }
}
