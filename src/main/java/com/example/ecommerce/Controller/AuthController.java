package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.LoginDto;
import com.example.ecommerce.Dto.UserDto;
import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Response.LoginResponse;
import com.example.ecommerce.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> storeUser(@Valid @RequestBody UserDto userdto, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){ return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}

        if (!userdto.getPassword().equals(userdto.getConfirmPassword())){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        return authenticationService.storeUser(userdto);
    }

    @PostMapping("/login")
    public ResponseEntity <LoginResponse> login(@Valid @RequestBody LoginDto logindto, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}

        return ResponseEntity.ok(authenticationService.login(logindto));
    }
}
