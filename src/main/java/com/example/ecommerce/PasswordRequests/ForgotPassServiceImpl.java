package com.example.ecommerce.PasswordRequests;

import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForgotPassServiceImpl implements ForgotPassTokenService{

    @Autowired
    private final ForgotPassTokenRep forgotPassTokenRep;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void initiateForgotPass(String userEmail){

        ForgotPassToken forgotPassToken = new ForgotPassToken();

        forgotPassToken.setToken(generateOTP());
        forgotPassTokenRep.save(forgotPassToken);
    }

    @Override
    public void resetPassword(ForgotPassToken forgotPassToken, String newPassword){
        if (!forgotPassToken.getToken().equals(forgotPassTokenRep.findByToken(forgotPassToken.getToken())));

        UserEntity userEntity = new UserEntity();
        if (userEntity != null){
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            ResponseEntity.badRequest().body("User not found");
        }
    }

    public String generateOTP(){
        List rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special,1));

        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8,rules);
        return password;
    }

}
