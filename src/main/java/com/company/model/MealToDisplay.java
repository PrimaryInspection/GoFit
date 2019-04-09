package com.company.model;

import java.io.Serializable;
import java.util.Objects;

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
    private Float protein;
    private Float fat;
    private Float carbs;

    public MealToDisplay() {
    }

    // constructor for displaying totals
    public MealToDisplay(Integer weight, Integer calories, Float protein, Float fat, Float carbs) {
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public MealToDisplay(Integer id, String mealNumber, String product, Integer weight, Integer calories, Float protein, Float fat, Float carbs) {
        this.id = id;
        this.mealType = mealNumber;
        this.mealItem = product;
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getMealType() {
        return mealType;
    }

    public String getProduct() {
        return mealItem;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public Float getProtein() {
        return protein;
    }

    public Float getFat() {
        return fat;
    }

    public Float getCarbs() {
        return carbs;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealToDisplay that = (MealToDisplay) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(mealType, that.mealType) &&
                Objects.equals(mealItem, that.mealItem) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(calories, that.calories) &&
                Objects.equals(protein, that.protein) &&
                Objects.equals(fat, that.fat) &&
                Objects.equals(carbs, that.carbs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mealType, mealItem, weight, calories, protein, fat, carbs);
    }

    @Override
    public String toString() {
        return "MealToDisplay{" +
                "id=" + id +
                ", mealType='" + mealType + '\'' +
                ", product='" + mealItem + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}
