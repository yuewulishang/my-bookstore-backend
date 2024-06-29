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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        try {
            User user = userService.findByUsername(loginRequestDto.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto(false, "没有该用户"));
            }
            boolean isValidUser = userService.validateUser(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            if (isValidUser) {
                request.getSession().setAttribute("user", user);
                ApiResponseDto responseDto = new ApiResponseDto(true, "登录成功");
                responseDto.setData(user.isAdmin()); // 将用户角色信息一并返回
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto(false, "密码不正确或账号被禁用"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponseDto(false, e.getMessage()));
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

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ResponseEntity<?> disableUser(@PathVariable Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok(new ApiResponseDto(true, "用户已禁用"));
    }

    @Override
    public ResponseEntity<?> enableUser(@PathVariable Long id) {
        userService.enableUser(id);
        return ResponseEntity.ok(new ApiResponseDto(true, "用户已启用"));
    }

    @Override
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }
}
