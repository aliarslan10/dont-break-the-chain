package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findOrCreate(String roleName);
    Role getBasicRole();
    Role getVipRole();
}