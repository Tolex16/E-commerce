package com.example.ecommerce.PasswordRequests;

import com.example.ecommerce.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    @Autowired
    private final ForgotPassTokenService forgotPassTokenService;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateForgotPass(@RequestBody UserEntity user){
        forgotPassTokenService.initiateForgotPass(user);
        return ResponseEntity.ok("Forgot Password sequence initiated. Check your email for instructions.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam ForgotPassToken token, @RequestParam String newPassword){
        forgotPassTokenService.resetPassword(token,newPassword);
        return ResponseEntity.ok("Password Reset successful.");
    }
}
