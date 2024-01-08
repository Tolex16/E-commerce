package com.example.ecommerce.Response;

import com.example.ecommerce.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponse {

    private final UserEntity user;

    private final JwtAuthResponse jwtAuthResponse;
}
