package com.example.aliarslan.dontbreakthechain.controller;

import com.example.aliarslan.dontbreakthechain.dto.HabitDTO;
import com.example.aliarslan.dontbreakthechain.model.Habit;
import com.example.aliarslan.dontbreakthechain.model.User;
import com.example.aliarslan.dontbreakthechain.request.SaveMultipleHabitRequest;
import com.example.aliarslan.dontbreakthechain.service.HabitService;
import com.example.aliarslan.dontbreakthechain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HabitController {

    @Autowired
    private HabitService habitService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/users/habits","/users/habits/vip"}, method = RequestMethod.POST)
    public ResponseEntity<Object> addHabit(@RequestBody SaveMultipleHabitRequest request, Errors errors) {

        if (errors.hasErrors()) {
            String result = errors.getAllErrors()
                    .stream()
                    .map(item -> item.getDefaultMessage())
                    .collect(Collectors.joining(","));
            return ResponseEntity.badRequest().body(result);
        } else {
            User user = userService.findByUsername(request.username);

            List<HabitDTO> habitDTO = request.data;

            Set<Habit> habitList = new HashSet<>();

            for (HabitDTO item: habitDTO) {
                Habit habit = new Habit();
                habit.setGoal(item.goal);
                habit.setUser(user);
                habit.setChain(item.days.toString());
                habitList.add(habit);
            }

            habitService.removeHabits(user.getHabit());

            user.setHabit(habitList);
            userService.update(user);

            HashMap<String, Object> json = new HashMap<>();
            json.put("status",HttpStatus.OK);
            json.put("message","Habits saved.");

            return ResponseEntity.ok(json);
        }
    }
}