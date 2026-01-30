package com.example.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @Autowired
 private UserRepository repo;

 @Autowired
 private JwtUtil jwt;

 @PostMapping("/register")
 public String register(@RequestBody User u){
  u.setPassword(
   new BCryptPasswordEncoder().encode(u.getPassword()));
  repo.save(u);
  return "REGISTERED";
 }

 @PostMapping("/login")
 public Map<String,String> login(@RequestBody User u){
  User db=repo.findByUsername(u.getUsername())
    .orElseThrow();
  String token=jwt.generateToken(
    db.getUsername(),db.getRole());
  return Map.of("token",token);
 }
}
