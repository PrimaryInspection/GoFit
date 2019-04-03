package com.company.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Meal implements Serializable {
    private Integer mealId;
    private Integer userId;
    private Integer mealTypeId;
    private Integer mealItemId;
    private Float weight;
    private LocalDate date;

    public Meal() {
    }

    public Meal(Integer userId, Integer mealTypeId, Integer mealItemId, Float weight, LocalDate date) {
        this.userId = userId;
        this.mealTypeId = mealTypeId;
        this.mealItemId = mealItemId;
        this.weight = weight;
        this.date = date;
    }

    public Meal(Integer mealId, Integer userId, Integer mealTypeId, Integer mealItemId, Float weight, LocalDate date) {
        this.mealId = mealId;
        this.userId = userId;
        this.mealTypeId = mealTypeId;
        this.mealItemId = mealItemId;
        this.weight = weight;
        this.date = date;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(Integer mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public Integer getMealItemId() {
        return mealItemId;
    }

    public void setMealItemId(Integer mealItemId) {
        this.mealItemId = mealItemId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return Objects.equals(getMealId(), meal.getMealId()) &&
                Objects.equals(getUserId(), meal.getUserId()) &&
                Objects.equals(getMealTypeId(), meal.getMealTypeId()) &&
                Objects.equals(getMealItemId(), meal.getMealItemId()) &&
                Objects.equals(getWeight(), meal.getWeight()) &&
                Objects.equals(getDate(), meal.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMealId(), getUserId(), getMealTypeId(), getMealItemId(), getWeight(), getDate());
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", userId=" + userId +
                ", mealTypeId=" + mealTypeId +
                ", mealItemId=" + mealItemId +
                ", weight=" + weight +
                ", date=" + date +
                '}';
    }
}
