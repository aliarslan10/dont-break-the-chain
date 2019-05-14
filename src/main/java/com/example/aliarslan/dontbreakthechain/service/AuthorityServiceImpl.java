package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import com.example.aliarslan.dontbreakthechain.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public void save(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public Authority getInfiniteHabitAddingAuthority() {
        String authorityName = "CAN_ADD_INFINITE_HABIT";
        Authority authority  = authorityRepository.findByName(authorityName);
        if (authority == null) {
            authority = new Authority();
            authority.setName(authorityName);
            authorityRepository.save(authority);
        }
        return authority;
    }
}
