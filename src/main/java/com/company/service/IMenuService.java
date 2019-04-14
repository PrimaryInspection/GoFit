package com.company.service;

import com.company.model.Meal;
import com.company.model.MealToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IMenuService {
    boolean addMeal(Meal meal);

    List<MealToDisplay> getUserMenu(int id, LocalDate date);

    int getTotalWeight(List<MealToDisplay> menu);

    int getTotalCalories(List<MealToDisplay> menu);

    int getTotalProteins(List<MealToDisplay> menu);

    int getTotalFat(List<MealToDisplay> menu);

    int getTotalCarbs(List<MealToDisplay> menu);

    MealToDisplay getTotalsByMealType(Integer userId , LocalDate date , Integer mealTypeId);

    MealToDisplay getUserMealTotal(Integer userId, LocalDate date);

    boolean deleteMealFromPage(int id);
}
