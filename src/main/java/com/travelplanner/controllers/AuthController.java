//package com.travelplanner.controllers;
//
//import com.travelplanner.models.User;
//import com.travelplanner.services.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    // ✅ Register a new user
//    @PostMapping("/register")
//    public String registerUser(@RequestBody User user) {
//        return authService.registerUser(user);
//    }
//
//    // ✅ Authenticate user & generate JWT token
//    @PostMapping("/login")
//    public Map<String, String> loginUser(@RequestBody Map<String, String> credentials) {
//        String token = authService.authenticateUser(credentials.get("username"), credentials.get("password"));
//        return Map.of("token", token);
//    }
//}
