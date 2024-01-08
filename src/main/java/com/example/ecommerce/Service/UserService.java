package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.UserDto;
import com.example.ecommerce.Entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {

    UserDetailsService userDetailsService();
}
