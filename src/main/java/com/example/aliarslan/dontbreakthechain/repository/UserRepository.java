package com.example.aliarslan.dontbreakthechain.repository;

import com.example.aliarslan.dontbreakthechain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMailAddress(String mailAddress);
    User findByUsername(String username);
}
