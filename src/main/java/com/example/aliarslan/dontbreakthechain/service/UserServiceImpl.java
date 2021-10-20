package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import com.example.aliarslan.dontbreakthechain.model.Role;
import com.example.aliarslan.dontbreakthechain.model.User;
import com.example.aliarslan.dontbreakthechain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {
        checkUserIfExist(user.getUsername());
        user.setRole(roleService.getBasicRole());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public  User getUser(String usernameOrMail){
        return getUserIfExist(usernameOrMail);
    }

    @Override
    public void updateUserRoleToVip(User userData) {
        User user = getUserIfExist(userData.getUsername());
        Role role = roleService.getVipRole();
        Authority authority = authorityService.getInfiniteHabitAddingAuthority();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        role.setAuthorities(authorities);

        user.setRole(role);
        userRepository.save(user);
    }

    private void checkUserIfExist(String usernameOrMail) {
        if (userRepository.findByUsernameOrMailAddress(usernameOrMail) != null) {
            throw new RuntimeException("This user already exist!");
        }
    }

    private User getUserIfExist(String usernameOrMail) {
        User user = userRepository.findByUsernameOrMailAddress(usernameOrMail);
        if (user == null) {
            throw new RuntimeException("User does not exist!");
        }
        return user;
    }
}
