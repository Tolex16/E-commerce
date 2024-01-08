package com.example.ecommerce.Security;

import com.example.ecommerce.Service.JwtService;
import com.example.ecommerce.Token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    //private static final String ATTRIBUTE_TO_CLEAR = "currentUser";
    @Autowired
    private final TokenService tokenService;

    private final JwtService jwtService;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

     //   clearSessionAttribute(request.getSession());
        String authHeader = request.getHeader("Authorization");
        final String jwt;
        String userEmail = null;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);
        tokenService.storeToken(null, jwt);
        var token = tokenService.getTokenByEmail(jwt);
        if (token != null){
            SecurityContextHolder.clearContext();
        }
    }
//
//    private void clearSessionAttribute(HttpSession session){
//        if (session != null){
//            session.removeAttribute(ATTRIBUTE_TO_CLEAR);
//        }
//    }
}
