package com.example.aliarslan.dontbreakthechain.controller;

import com.example.aliarslan.dontbreakthechain.model.Authority;
import com.example.aliarslan.dontbreakthechain.model.Role;
import com.example.aliarslan.dontbreakthechain.model.User;
import com.example.aliarslan.dontbreakthechain.service.AuthorityService;
import com.example.aliarslan.dontbreakthechain.service.RoleService;
import com.example.aliarslan.dontbreakthechain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping(value = "/users/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUser(@Valid @RequestBody User data, Errors errors){

        if (errors.hasErrors()) {

            String result = errors.getAllErrors()
                    .stream()
                    .map(item -> item.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            HashMap<String, Object> json = new HashMap<>();
            json.put("status",HttpStatus.BAD_REQUEST);
            json.put("message",result);

            return ResponseEntity.badRequest().body(json);

        } else {

            HashMap<String, Object> json = new HashMap<>();

            if (userService.findByUsername(data.getUsername()) != null) {

                json.put("status", HttpStatus.BAD_REQUEST);
                json.put("message","This username is already exist");

                return ResponseEntity.badRequest().body(json);

            } else if (userService.findByEMailAddress(data.getMailAddress()) != null) {

                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message","This mail address is already exist");

                    return ResponseEntity.badRequest().body(json);

            } else {

                User user = new User();
                user.setUsername(data.getUsername());
                user.setMailAddress(data.getMailAddress());
                user.setPassword(data.getPassword());

                Role role = roleService.getBasicRole();

                user.setRole(role);

                userService.save(user);

                json.put("status", HttpStatus.OK);
                json.put("message","User registered");
                json.put("date",user);

                return ResponseEntity.ok(json);
            }
        }
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@NotBlank @PathVariable("username") String username){

        User user = userService.findByUsername(username);
        HashMap<String, Object> json = new HashMap<>();

        if (user != null) {

            json.put("status", HttpStatus.OK);
            json.put("message","User details");
            json.put("data",user);
            return ResponseEntity.ok(json);

        } else {

            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message","User not found");
            return ResponseEntity.badRequest().body(json);
        }
    }

    @RequestMapping(value = "/users/roles/vip", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRoleToVip(@RequestBody User userData){

        User user = userService.findByUsername(userData.getUsername());
        Role role = roleService.getVipRole();
        Authority authority = authorityService.getInfiniteHabitAddingAuthority();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        role.setAuthorities(authorities);

        user.setRole(role);
        userService.update(user);

        return ResponseEntity.ok("ok");
    }
}
