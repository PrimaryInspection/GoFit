package com.company.service;

import com.company.model.Meal;
import com.company.model.MealToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IMenuService {

    boolean addMeal(Meal meal);

    List<MealToDisplay> getUserMenu(int id, LocalDate chosenDate);

    int getTotalWeight(List<MealToDisplay> menu);

    String getTotalCalories(List<MealToDisplay> menu);

    String getTotalProteins(List<MealToDisplay> menu);

    String getTotalFat(List<MealToDisplay> menu);

    String getTotalCarbs(List<MealToDisplay> menu);

    MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId);

    MealToDisplay getUserFoodTotal(Integer userId, LocalDate date);

    boolean deleteFromFoodDiary(int id);

}
