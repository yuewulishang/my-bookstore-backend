package com.example.backend.controllerImpl;

import com.example.backend.controller.UserController;
import com.example.backend.domains.User;
import com.example.backend.dto.ApiResponseDto;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.RegisterRequestDto;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        User user = userService.findByUsername(loginRequestDto.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto(false, "没有该用户"));
        }
        boolean isValidUser = userService.validateUser(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (isValidUser) {
            request.getSession().setAttribute("user", user); // 设置会话信息
            return ResponseEntity.ok(new ApiResponseDto(true, "登录成功"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto(false, "密码不正确"));
        }
    }

    @Override
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        boolean isRegistered = userService.registerUser(registerRequestDto);
        if (isRegistered) {
            return ResponseEntity.ok(new ApiResponseDto(true, "注册成功"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(false, "注册失败"));
        }
    }
}
