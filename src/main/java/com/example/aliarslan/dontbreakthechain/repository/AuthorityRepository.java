package com.example.aliarslan.dontbreakthechain.repository;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}