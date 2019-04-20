package com.company.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class ActivityToDisplay implements Serializable {
    private Integer id;
    private String activityName;
    private Integer timeSpent;
    private Integer calories;

    public ActivityToDisplay() {
    }

    public ActivityToDisplay( Integer timeSpent, Integer calories) {

        this.timeSpent = timeSpent;
        this.calories = calories;
    }

    public ActivityToDisplay(Integer id, String activity, Integer timeSpent, Integer calories) {
        this.id = id;
        this.activityName = activity;
        this.timeSpent = timeSpent;
        this.calories = calories;
    }

    public String getActivity() {
        return activityName;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityToDisplay that = (ActivityToDisplay) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(timeSpent, that.timeSpent) &&
                Objects.equals(calories, that.calories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, activityName, timeSpent, calories);
    }

    @Override
    public String toString() {
        return "ActivityDiaryToDisplay{" +
                "id=" + id +
                ", activity='" + activityName + '\'' +
                ", timeSpent=" + timeSpent +
                ", calories=" + calories +
                '}';
    }
}
