package com.example.aliarslan.dontbreakthechain.repository;

import com.example.aliarslan.dontbreakthechain.model.Habit;
import com.example.aliarslan.dontbreakthechain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    Habit save(List<Habit> habbitList);
    List<Habit> deleteByUserId(long userId);
    List<Habit> findByUser(User user);
}