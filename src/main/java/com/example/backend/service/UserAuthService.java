package com.example.backend.service;

import com.example.backend.domains.UserAuth;
import com.example.backend.repo.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    public List<UserAuth> getAllUserAuths() {
        return userAuthRepository.findAll();
    }

    public UserAuth getUserAuthById(Long id) {
        Optional<UserAuth> userAuth = userAuthRepository.findById(id);
        return userAuth.orElse(null);
    }

    public UserAuth createUserAuth(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }

    public UserAuth updateUserAuth(Long id, UserAuth userAuthDetails) {
        Optional<UserAuth> userAuth = userAuthRepository.findById(id);
        if (userAuth.isPresent()) {
            UserAuth existingUserAuth = userAuth.get();
            existingUserAuth.setAuthType(userAuthDetails.getAuthType());
            existingUserAuth.setAuthValue(userAuthDetails.getAuthValue());
            existingUserAuth.setCreatedAt(userAuthDetails.getCreatedAt());
            existingUserAuth.setUpdatedAt(userAuthDetails.getUpdatedAt());
            return userAuthRepository.save(existingUserAuth);
        }
        return null;
    }

    public void deleteUserAuth(Long id) {
        userAuthRepository.deleteById(id);
    }
}
