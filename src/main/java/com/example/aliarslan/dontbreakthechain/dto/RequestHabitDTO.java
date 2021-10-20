package com.example.aliarslan.dontbreakthechain.dto;

import java.util.List;

public class RequestHabitDTO {

    public String goal;
    public List<String> days;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
