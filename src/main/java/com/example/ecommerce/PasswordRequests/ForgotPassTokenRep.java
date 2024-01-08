package com.example.ecommerce.PasswordRequests;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPassTokenRep extends JpaRepository<ForgotPassToken, Long> {
    Optional<ForgotPassToken> findByToken(String token);
}
