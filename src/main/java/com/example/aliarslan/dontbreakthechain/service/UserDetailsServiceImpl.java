package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import com.example.aliarslan.dontbreakthechain.model.Role;
import com.example.aliarslan.dontbreakthechain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<Role> roleList = new ArrayList<>();
        Role role = user.getRole();
        roleList.add(role);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(roleList));
    }


    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }


    private List<String> getPrivileges(List<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Authority> collection = new ArrayList<>();

        for (Role role : roles) {
            collection.addAll(role.getAuthorities());
        }

        for (Authority item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }


    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
