package com.ridehailing.controller;

import com.ridehailing.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ridehailing.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users users){

        Users savedUser = userService.register(users);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
        String token = userService.verify(user);

        if("Fail".equals(token)){
            return ResponseEntity.status(401).body("Invalid Credentials");
        }

        return ResponseEntity.ok(token);
    }


}
