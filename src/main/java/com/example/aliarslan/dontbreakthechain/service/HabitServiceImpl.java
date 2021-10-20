package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.dto.RequestHabitDTO;
import com.example.aliarslan.dontbreakthechain.dto.ResponseHabitDto;
import com.example.aliarslan.dontbreakthechain.model.Habit;
import com.example.aliarslan.dontbreakthechain.model.User;
import com.example.aliarslan.dontbreakthechain.repository.HabitRepository;
import com.example.aliarslan.dontbreakthechain.repository.UserRepository;
import com.example.aliarslan.dontbreakthechain.request.SaveMultipleHabitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class HabitServiceImpl implements HabitService {

    @Autowired
    HabitRepository habitRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void add(SaveMultipleHabitRequest saveMultipleHabit) {
        User user = userService.getUser(saveMultipleHabit.getUsername());

        Set<Habit> habitList = new HashSet<>();

        for (RequestHabitDTO item: saveMultipleHabit.getHabits()) {
            Habit habit = new Habit();
            habit.setGoal(item.goal);
            habit.setUser(user);
            habit.setChain(item.days.toString());
            habitList.add(habit);
        }

        habitRepository.deleteInBatch(user.getHabit());

        user.setHabit(habitList);
        userRepository.save(user);
    }

    @Override
    public void remove(Set<Habit> habitList) {
        habitRepository.deleteInBatch(habitList);
    }

    @Override
    public List<ResponseHabitDto> getAllHabitsForRelatedUser(String username) {
        List<ResponseHabitDto> responseHabitDtoList = new LinkedList<>();
        User user = userService.getUser(username);
        habitRepository.findByUser(user).forEach(habit -> {
            ResponseHabitDto responseHabitDto = new ResponseHabitDto();
            responseHabitDto.setGoal(habit.getGoal());
            responseHabitDto.setDays(habit.getChain());
            responseHabitDto.setUsername(habit.getUser().getUsername());
            responseHabitDtoList.add(responseHabitDto);
        });
        return responseHabitDtoList;
    }
}
