package com.example.aliarslan.dontbreakthechain.controller;

import com.example.aliarslan.dontbreakthechain.request.SaveMultipleHabitRequest;
import com.example.aliarslan.dontbreakthechain.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping(value = {"/users/habits","/users/habits/vip"})
    public ResponseEntity<Object>  addHabit(@RequestBody SaveMultipleHabitRequest request) {
        habitService.add(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping(value = {"/users/habits/{username}","/users/habits/vip/{username}"})
    public ResponseEntity<Object>  getAllHabitsForRelatedUser(@PathVariable String username) {
        return ResponseEntity.ok(habitService.getAllHabitsForRelatedUser(username));
    }
}