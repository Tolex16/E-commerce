package com.example.ecommerce.PasswordRequests;

public interface ForgotPassTokenService {
    void initiateForgotPass(String userEmail);
    void resetPassword(ForgotPassToken forgotPassToken, String newPassword);
}
