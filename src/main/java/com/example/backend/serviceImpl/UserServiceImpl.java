package com.example.backend.serviceImpl;

import com.example.backend.domains.User;
import com.example.backend.domains.UserAuth;
import com.example.backend.dto.RegisterRequestDto;
import com.example.backend.repo.UserRepository;
import com.example.backend.repo.UserAuthRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!user.isEnabled()) {
            throw new RuntimeException("您的账号已经被禁用");
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
        user.setPhoneNumber(registerRequestDto.getPhoneNumber());
        user.setAdmin(false);  // 默认新注册用户为顾客
        user.setEnabled(true);
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

    @Override
    public void disableUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void enableUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
