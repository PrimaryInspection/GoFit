package com.company.model.entity;

import java.io.Serializable;

/**
 * This class is used to show meal entries and meal TOTAL values to user.
 * It contains:
 * mealType name and product name instead of their id;
 * calories, protein, fat and carbs of the product according to its weight (not for 100 grams)
 */
public class MealToDisplay implements Serializable {
    private Integer id;
    private String mealType;
    private String mealItem;
    private Integer weight;
    private Integer calories;
    private Integer proteins;
    private Integer fats;
    private Integer carbs;

    public MealToDisplay() {
    }

    // constructor for displaying totals
    public MealToDisplay(Integer weight, Integer calories, Integer proteins, Integer fats, Integer carbs) {
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public MealToDisplay(Integer id, String mealNumber, String product, Integer weight, Integer calories, Integer proteins, Integer fats, Integer carbs) {
        this.id = id;
        this.mealType = mealNumber;
        this.mealItem = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealItem() {
        return mealItem;
    }

    public void setMealItem(String mealItem) {
        this.mealItem = mealItem;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealToDisplay)) return false;

        MealToDisplay meal = (MealToDisplay) o;

        if (!getId().equals(meal.getId())) return false;
        if (!getMealType().equals(meal.getMealType())) return false;
        if (!getMealItem().equals(meal.getMealItem())) return false;
        if (!getWeight().equals(meal.getWeight())) return false;
        if (!getCalories().equals(meal.getCalories())) return false;
        if (!getProteins().equals(meal.getProteins())) return false;
        if (!getFats().equals(meal.getFats())) return false;
        return getCarbs().equals(meal.getCarbs());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getMealType().hashCode();
        result = 31 * result + getMealItem().hashCode();
        result = 31 * result + getWeight().hashCode();
        result = 31 * result + getCalories().hashCode();
        result = 31 * result + getProteins().hashCode();
        result = 31 * result + getFats().hashCode();
        result = 31 * result + getCarbs().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MealToDisplay{" +
                "id=" + id +
                ", mealType='" + mealType + '\'' +
                ", mealItem='" + mealItem + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbs=" + carbs +
                '}';
    }
}
