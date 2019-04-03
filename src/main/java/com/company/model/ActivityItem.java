package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class ActivityItem implements Serializable {
    private Integer activityItemId;
    private String name;
    private Integer calories;

    public ActivityItem() {
    }

    public ActivityItem(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public ActivityItem(Integer activityItemId, String name, Integer calories) {
        this.activityItemId = activityItemId;
        this.name = name;
        this.calories = calories;
    }

    public Integer getActivityItemId() { return activityItemId; }

    public void setActivityItemId(Integer activityItemId) { this.activityItemId = activityItemId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getCalories() { return calories; }

    public void setCalories(Integer calories) { this.calories = calories; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityItem)) return false;
        ActivityItem that = (ActivityItem) o;
        return Objects.equals(getActivityItemId(), that.getActivityItemId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCalories(), that.getCalories());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getActivityItemId(), getName(), getCalories());
    }

    @Override
    public String toString() {
        return "ActivityItem{" +
                "activityItemId=" + activityItemId +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
