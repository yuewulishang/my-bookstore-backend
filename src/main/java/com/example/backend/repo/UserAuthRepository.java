package com.example.backend.repo;

import com.example.backend.domains.User;
import com.example.backend.domains.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUserAndAuthType(User user, String authType);
}
