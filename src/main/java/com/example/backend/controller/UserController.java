package com.example.backend.controller;

import com.example.backend.domains.User;
import com.example.backend.dto.ApiResponseDto;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.RegisterRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // 允许来自 http://localhost:3000 的请求
public interface UserController {

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request);

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto);

    @GetMapping("/users")
    List<User> getAllUsers();

    @PostMapping("/users/{id}/disable")
    ResponseEntity<?> disableUser(@PathVariable Long id);

    @PostMapping("/users/{id}/enable")
    ResponseEntity<?> enableUser(@PathVariable Long id);

    @GetMapping("/users/exists/{username}")
    ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username);
}
