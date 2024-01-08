package com.example.ecommerce.Service.ServiceImpl;

import com.example.ecommerce.Dto.LoginDto;
import com.example.ecommerce.Dto.UserDto;
import com.example.ecommerce.Entity.Role;
import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Response.JwtAuthResponse;
import com.example.ecommerce.Response.LoginResponse;
import com.example.ecommerce.Service.AuthenticationService;
import com.example.ecommerce.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<UserEntity> storeUser(UserDto userdto){
        if (userRepository.existsByEmail(userdto.getEmail())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setFirstName(userdto.getFirstName());
        user.setMiddleName(userdto.getMiddleName());
        user.setLastName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setDateOfBirth(userdto.getDateOfBirth());
        user.setPhoneNumber(userdto.getPhoneNumber());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(userdto.getConfirmPassword()));

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public boolean isCurrentPasswordValid(String currentPassword) {
        UserEntity currentUser = getCurrentUser();
        return currentUser != null && passwordEncoder.matches(currentPassword, currentUser.getPassword());
    }

    @Override
    public void updatePassword(String newPassword) {
        UserEntity currentUser = getCurrentUser();
        if (currentUser != null){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(currentUser);
        } else {
            ResponseEntity.badRequest().body("User not found");
        }
    }

    private static UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity){
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }
    public LoginResponse login(LoginDto loginDto)
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new IllegalArgumentException("Invalid email and Password", e);
        }

        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Error in email and password"));
        var jwt = jwtService.genToken(user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);

        return new LoginResponse(user, jwtAuthResponse);
    }
}
