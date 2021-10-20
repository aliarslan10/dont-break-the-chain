package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.User;

public interface UserService {

    User save(User user);
    User getUser(String usernameOrMail);
    void updateUserRoleToVip(User user);
}