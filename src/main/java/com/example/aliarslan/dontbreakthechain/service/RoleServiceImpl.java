package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Role;
import com.example.aliarslan.dontbreakthechain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findOrCreate(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null){
            role = new Role();
            role.setName("ROLE_BASIC");
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public Role getBasicRole(){
        String roleName = "ROLE_BASIC";
        Role role = roleRepository.findByName(roleName);
        if (role == null){
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public Role getVipRole(){
        String roleName = "ROLE_VIP";
        Role role = roleRepository.findByName(roleName);
        if (role == null){
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        return role;
    }
}