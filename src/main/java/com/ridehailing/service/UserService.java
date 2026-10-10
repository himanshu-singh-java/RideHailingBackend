package com.ridehailing.service;

import com.ridehailing.model.Role;
import com.ridehailing.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ridehailing.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRole() == null){
            user.setRole(Role.ROLE_RIDER);
        }

        return userRepository.save(user);
    }

    public String verify(Users user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getEmail());
        }
        return "Fail";
    }
}
