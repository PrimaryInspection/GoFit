package com.company.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class MealItem implements Serializable {
    private Integer mealItemId;
    private String name;
    private Integer calories;
    private Integer fats;
    private Integer proteins;
    private Integer carbs;

    public MealItem() {
    }

    public MealItem(String name, Integer fats, Integer calories, Integer proteins, Integer carbs) {
        this.name = name;
        this.fats = fats;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
    }

    public MealItem(Integer mealItemId, String name, Integer fats, Integer calories, Integer proteins, Integer carbs) {
        this.mealItemId = mealItemId;
        this.name = name;
        this.fats = fats;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
    }

    public Integer getMealItemId() { return mealItemId; }

    public void setMealItemId(Integer mealItemId) { this.mealItemId = mealItemId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getCalories() { return calories; }

    public void setCalories(Integer calories) { this.calories = calories; }

    public Integer getFats() { return fats; }

    public void setFats(Integer fats) { this.fats = fats; }

    public Integer getProteins() { return proteins; }

    public void setProteins(Integer proteins) { this.proteins = proteins; }

    public Integer getCarbs() { return carbs; }

    public void setCarbs(Integer carbs) { this.carbs = carbs; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealItem)) return false;
        MealItem mealItem = (MealItem) o;
        return Objects.equals(mealItemId, mealItem.mealItemId) &&
                Objects.equals(name, mealItem.name) &&
                Objects.equals(fats, mealItem.fats) &&
                Objects.equals(calories, mealItem.calories) &&
                Objects.equals(proteins, mealItem.proteins) &&
                Objects.equals(carbs, mealItem.carbs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mealItemId, name, fats, calories, proteins, carbs);
    }

    @Override
    public String toString() {
        return "MealItem{" +
                "mealItemId=" + mealItemId +
                ", name='" + name + '\'' +
                ", fats=" + fats +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", carbs=" + carbs +
                '}';
    }
}
