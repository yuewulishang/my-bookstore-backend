package com.example.backend.service;

import com.example.backend.domains.User;
import com.example.backend.dto.RegisterRequestDto;

import java.util.List;

public interface UserService {
    boolean validateUser(String username, String password);
    boolean registerUser(RegisterRequestDto registerRequestDto);
    User findByUsername(String username);
    void disableUser(Long userId);
    void enableUser(Long userId);
    List<User> getAllUsers();
    boolean existsByUsername(String username);
}
