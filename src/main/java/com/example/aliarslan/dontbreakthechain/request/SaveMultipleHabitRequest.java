package com.example.aliarslan.dontbreakthechain.request;

import com.example.aliarslan.dontbreakthechain.dto.RequestHabitDTO;

import java.util.List;

public class SaveMultipleHabitRequest {

    private String username;
    private List<RequestHabitDTO> habits;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RequestHabitDTO> getHabits() {
        return habits;
    }

    public void setHabits(List<RequestHabitDTO> habits) {
        this.habits = habits;
    }
}
