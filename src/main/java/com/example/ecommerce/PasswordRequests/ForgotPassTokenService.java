package com.example.ecommerce.PasswordRequests;

import com.example.ecommerce.Entity.UserEntity;

public interface ForgotPassTokenService {
    void initiateForgotPass(UserEntity user);
    void resetPassword(ForgotPassToken forgotPassToken, String newPassword);
}
