package com.example.ecommerce.Token;

import java.util.HashMap;
import java.util.Map;

public class TokenService {
    private Map<String, String > emailToTokenMap = new HashMap<>();


    public void storeToken(String email, String token){
        emailToTokenMap.put(email,token);
    }

    public String getTokenByEmail(String email){
        return emailToTokenMap.get(email);
    }
}
