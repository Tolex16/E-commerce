package com.example.ecommerce.Service.ServiceImpl;

import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.JwtService;
import com.example.ecommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserRepository userRepository;


    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
            }
        };
    }

}
