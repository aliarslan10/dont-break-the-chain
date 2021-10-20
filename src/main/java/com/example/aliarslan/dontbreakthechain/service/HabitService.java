package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.dto.ResponseHabitDto;
import com.example.aliarslan.dontbreakthechain.model.Habit;
import com.example.aliarslan.dontbreakthechain.request.SaveMultipleHabitRequest;

import java.util.List;
import java.util.Set;

public interface HabitService {
    void add(SaveMultipleHabitRequest saveMultipleHabit);
    List<ResponseHabitDto> getAllHabitsForRelatedUser(String username);
    void remove(Set<Habit> habitList);
}