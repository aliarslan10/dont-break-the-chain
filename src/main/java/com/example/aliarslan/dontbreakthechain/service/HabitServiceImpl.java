package com.example.aliarslan.dontbreakthechain.service;

import com.example.aliarslan.dontbreakthechain.model.Habit;
import com.example.aliarslan.dontbreakthechain.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HabitServiceImpl implements HabitService {

    @Autowired
    HabitRepository habitRepository;

    @Override
    public void removeHabits(Set<Habit> habitList) {
        habitRepository.deleteInBatch(habitList);
    }
}
