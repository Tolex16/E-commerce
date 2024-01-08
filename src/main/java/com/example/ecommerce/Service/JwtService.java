package com.example.ecommerce.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractEmail(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String genToken(UserDetails userDetails);
}
