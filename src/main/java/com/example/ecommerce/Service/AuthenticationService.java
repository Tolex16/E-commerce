package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.LoginDto;
import com.example.ecommerce.Dto.UserDto;
import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    ResponseEntity<UserEntity> storeUser(UserDto userdto);

    boolean isCurrentPasswordValid(String currentPassword);

    void updatePassword(String newPassword);
    LoginResponse login(LoginDto loginDto);
}
