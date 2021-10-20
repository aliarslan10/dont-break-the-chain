package com.example.aliarslan.dontbreakthechain.controller;

import com.example.aliarslan.dontbreakthechain.model.User;
import com.example.aliarslan.dontbreakthechain.service.AuthorityService;
import com.example.aliarslan.dontbreakthechain.service.RoleService;
import com.example.aliarslan.dontbreakthechain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @PostMapping(value = "/users/register")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User data){
        return ResponseEntity.ok(userService.save(data));
    }

    @RequestMapping(value = "/users/{usernameOrMail}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@NotBlank @PathVariable("usernameOrMail") String usernameOrMail){
        return ResponseEntity.ok(userService.getUser(usernameOrMail));
    }

    @RequestMapping(value = "/users/roles/vip", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRoleToVip(@RequestBody User user){
        userService.updateUserRoleToVip(user);
        return ResponseEntity.ok(user);
    }
}
