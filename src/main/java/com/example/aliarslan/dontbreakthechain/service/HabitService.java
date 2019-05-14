package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Habit;

import java.util.Set;

public interface HabitService {
    void removeHabits(Set<Habit> habitList);
}