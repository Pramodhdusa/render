package com.example.jwt.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.entity.User;
import com.example.jwt.security.JwtUtil;
import com.example.jwt.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Registration API
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "User Registered Successfully";
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

    Optional<User> existingUser =
            userService.login(user.getUsername(), user.getPassword());

    if (existingUser.isPresent()) {

        User u = existingUser.get();

        String token = jwtUtil.generateToken(u.getUsername());

        HashMap<String,Object> response = new HashMap<>();

        response.put("message","Login Success");
        response.put("token",token);
        response.put("role",u.getRole().name());
        response.put("name",u.getName());

        return ResponseEntity.ok(response);

    } else {

        HashMap<String,String> error = new HashMap<>();
        error.put("message","Invalid Username or Password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
}