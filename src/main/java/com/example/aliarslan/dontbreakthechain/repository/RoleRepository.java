package com.example.aliarslan.dontbreakthechain.repository;

import com.example.aliarslan.dontbreakthechain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    List<Role> findAll();
}