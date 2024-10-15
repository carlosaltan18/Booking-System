package com.carlosaltan.bookingSystem.service;


import com.carlosaltan.bookingSystem.Dto.AuthDto;
import com.carlosaltan.bookingSystem.Dto.LoginDto;
import com.carlosaltan.bookingSystem.Dto.RegisterDto;
import com.carlosaltan.bookingSystem.config.JwtService;
import com.carlosaltan.bookingSystem.model.Usuario;
import com.carlosaltan.bookingSystem.repository.UserReposiroty;
import com.carlosaltan.bookingSystem.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserReposiroty userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthDto login(final LoginDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthDto(token);
    }

    public AuthDto register(final RegisterDto request){
        Usuario user = new Usuario();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        this.userRepository.save(user);
        return new AuthDto(this.jwtService.getToken(user));
    }

}