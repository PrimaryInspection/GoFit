package com.company.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Activity implements Serializable {
    private Integer activityId;
    private Integer userId;
    private Integer activityItemId;
    private Integer timeSpent;
    private LocalDate date;

    public Activity(){

    }

    public Activity(Integer activityId, Integer activityItemId, Integer userId, Integer timeSpent, LocalDate date) {
        this.activityId = activityId;
        this.activityItemId = activityItemId;
        this.userId = userId;
        this.timeSpent = timeSpent;
        this.date = date;
    }

    public Activity(Integer activityItemId, Integer userId, Integer timeSpent, LocalDate date) {
        this.activityItemId = activityItemId;
        this.userId = userId;
        this.timeSpent = timeSpent;
        this.date = date;
    }

    public Integer getId() { return activityId; }

    public void setId(Integer activityId) {  this.activityId = activityId; }

    public Integer getActivityItemId() { return activityItemId; }

    public void setActivityItemId(Integer activityItemId) { this.activityItemId = activityItemId; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTimeSpent() { return timeSpent; }

    public void setTimeSpent(Integer timeSpent) { this.timeSpent = timeSpent; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId) &&
                Objects.equals(getActivityItemId(), activity.getActivityItemId()) &&
                Objects.equals(getUserId(), activity.getUserId()) &&
                Objects.equals(getTimeSpent(), activity.getTimeSpent()) &&
                Objects.equals(getDate(), activity.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(activityId, getActivityItemId(), getUserId(), getTimeSpent(), getDate());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + activityId +
                ", activityItemId=" + activityItemId +
                ", userId=" + userId +
                ", timeSpent=" + timeSpent +
                ", date=" + date +
                '}';
    }
}
