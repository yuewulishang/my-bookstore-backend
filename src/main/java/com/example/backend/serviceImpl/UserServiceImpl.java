package com.example.backend.serviceImpl;

import com.example.backend.domains.User;
import com.example.backend.domains.UserAuth;
import com.example.backend.dto.RegisterRequestDto;
import com.example.backend.repo.UserRepository;
import com.example.backend.repo.UserAuthRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        UserAuth userAuth = userAuthRepository.findByUserAndAuthType(user, "PASSWORD");
        return userAuth != null && userAuth.getAuthValue().equals(password);
    }

    @Override
    public boolean registerUser(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername()) || userRepository.existsByEmail(registerRequestDto.getEmail())) {
            return false;
        }
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(registerRequestDto.getPassword());
        user.setEmail(registerRequestDto.getEmail());
        user.setPhoneNumber(registerRequestDto.getPhoneNumber()); // 确保保存 phoneNumber
        userRepository.save(user);

        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
        userAuth.setAuthType("PASSWORD");
        userAuth.setAuthValue(registerRequestDto.getPassword());
        userAuthRepository.save(userAuth);

        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
