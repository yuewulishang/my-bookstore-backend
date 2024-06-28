package com.example.backend.controller;

import com.example.backend.dto.ApiResponseDto;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.RegisterRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // 允许来自 http://localhost:3000 的请求
public interface UserController {

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request);

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto);
}
