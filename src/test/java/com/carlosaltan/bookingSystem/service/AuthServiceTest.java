package com.carlosaltan.bookingSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.carlosaltan.bookingSystem.Dto.AuthDto;
import com.carlosaltan.bookingSystem.Dto.LoginDto;
import com.carlosaltan.bookingSystem.Dto.RegisterDto;
import com.carlosaltan.bookingSystem.config.JwtService;
import com.carlosaltan.bookingSystem.model.Usuario;
import com.carlosaltan.bookingSystem.repository.UserReposiroty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserReposiroty userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_Success() {

        LoginDto loginDto = new LoginDto("user@example.com", "password");

        Usuario usuario = new Usuario();
        usuario.setEmail(loginDto.getEmail());
        usuario.setPassword("encoded_password");

        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(usuario));
        when(jwtService.getToken(usuario)).thenReturn("jwt_token");

        AuthDto result = authService.login(loginDto);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("jwt_token", result.getToken());
    }


    @Test
    void login_InvalidCredentials() {

        LoginDto loginDto = new LoginDto("invalid@example.com", "password");
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(loginDto));
    }


    @Test
    void register_Success() {
        RegisterDto registerDto = new RegisterDto("John", "Doe", "1234567890123", "john@example.com", "password");
        Usuario user = new Usuario();
        user.setEmail(registerDto.getEmail());

        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encoded_password");
        when(jwtService.getToken(any())).thenReturn("jwt_token");
        when(userRepository.save(any(Usuario.class))).thenReturn(user);

        AuthDto result = authService.register(registerDto);

        assertEquals("jwt_token", result.getToken());
        verify(userRepository).save(any(Usuario.class));
    }

    @Test
    void register_InvalidDpi() {

        RegisterDto registerDto = new RegisterDto("John", "Doe", "invalid_dpi", "john@example.com", "password");

        assertThrows(IllegalArgumentException.class, () -> authService.register(registerDto));
    }


    @Test
    void login_AuthenticationFails() {
        LoginDto loginDto = new LoginDto("user@example.com", "wrong_password");
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> authService.login(loginDto));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

}