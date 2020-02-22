package com.usmanadio.jwtsecurity.repositories;

import com.usmanadio.jwtsecurity.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findUserById(UUID id);
}
