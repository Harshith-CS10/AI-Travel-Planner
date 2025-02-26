//package com.travelplanner.services;
//
//import com.travelplanner.models.User;
//import com.travelplanner.repositories.UserRepository;
//import com.travelplanner.security.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public String registerUser(User user) {
//        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
//
//        if (existingUser.isPresent()) {
//            throw new RuntimeException("User already exists!");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "User registered successfully!";
//    }
//
//    public String authenticateUser(String username, String password) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return jwtUtil.generateToken(username); // Generate JWT Token
//            } else {
//                throw new RuntimeException("Invalid credentials");
//            }
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    }
//}
//
