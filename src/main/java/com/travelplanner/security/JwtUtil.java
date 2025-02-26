//package com.travelplanner.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET_KEY = "MySuperSecretKeyForJWTTokenGeneration"; // Use a strong secret
//
//    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hrs
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validateToken(String token, String username) {
//        try {
//            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            return claims.getSubject().equals(username) && claims.getExpiration().after(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
