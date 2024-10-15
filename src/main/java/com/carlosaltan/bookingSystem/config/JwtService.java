package com.carlosaltan.bookingSystem.config;

import com.carlosaltan.bookingSystem.util.Role;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private static final String ROLES_KEY = "roles"; // Clave para los roles

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        extraClaims.put(ROLES_KEY, roles);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public List<Role> getRolesFromToken(String token) {
        Claims claims = getAllClaims(token);
        List<String> roles = claims.get(ROLES_KEY, List.class); // Cambia "roles" a ROLES_KEY

        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            try {
                roleList.add(Role.valueOf(role)); // Asegúrate de que el nombre coincida exactamente
            } catch (IllegalArgumentException e) {
                System.out.println("Rol desconocido en el token: " + role);
            }
        }
        return roleList;
    }


    public String getUsernameFromToken(String token) {
        try {
            String username = getClaim(token, Claims::getSubject);
            System.out.println("Token: " + token);
            System.out.println("Username extraído: " + username);
            return username;
        } catch (Exception e) {
            System.out.println("Error extrayendo username del token: " + e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        if (username == null || !username.equals(userDetails.getUsername())) {
            System.out.println("Username no coincide: token=" + username + ", user=" + userDetails.getUsername());
            return false;
        }
        if (isTokenExpired(token)) {
            System.out.println("Token expirado.");
            return false;
        }

        List<Role> rolesFromToken = getRolesFromToken(token);
        if (!rolesFromToken.contains(Role.ROLE_USER)) {
            System.out.println("Rol no autorizado en el token.");
            return false;
        }
        return true;
    }


    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}