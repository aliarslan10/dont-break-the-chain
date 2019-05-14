package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.User;

public interface UserService {

    void save(User user);
    void update(User user);
    User findByEMailAddress(String mailAddress);
    User findByUsername(String mailAddress);
}