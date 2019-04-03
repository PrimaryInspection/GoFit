package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class MealType implements Serializable {
    private Integer mealTypeId;
    private String name;

    public MealType() {
    }

    public MealType(Integer mealTypeId, String name) {
        this.mealTypeId = mealTypeId;
        name = name;
    }

    public Integer getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(Integer mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealType)) return false;
        MealType mealType = (MealType) o;
        return Objects.equals(getMealTypeId(), mealType.getMealTypeId()) &&
                Objects.equals(getName(), mealType.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMealTypeId(), getName());
    }

    @Override
    public String toString() {
        return "MealType{" +
                "mealTypeId=" + mealTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
