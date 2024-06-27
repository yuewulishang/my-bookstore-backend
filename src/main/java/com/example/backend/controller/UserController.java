package com.example.backend.controller;

import com.example.backend.domains.User;
import com.example.backend.payload.ApiResponse;
import com.example.backend.payload.LoginRequest;
import com.example.backend.payload.RegisterRequest;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // 允许来自 http://localhost:3000 的请求
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "没有该用户"));
        }
        boolean isValidUser = userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (isValidUser) {
            return ResponseEntity.ok(new ApiResponse(true, "登录成功"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "密码不正确"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        boolean isRegistered = userService.registerUser(registerRequest);
        if (isRegistered) {
            return ResponseEntity.ok(new ApiResponse(true, "注册成功"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "注册失败"));
        }
    }
}
