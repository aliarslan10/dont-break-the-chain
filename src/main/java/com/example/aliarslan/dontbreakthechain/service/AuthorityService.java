package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {
    void save(Authority authority);
    Authority getInfiniteHabitAddingAuthority();
}
