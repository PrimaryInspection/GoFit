package com.company.model.dao.mapper;

import com.company.model.entity.Meal;
import com.company.model.entity.MealToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface MealDao extends CrudDao<Meal> {
    List<MealToDisplay> getMenu(int userId, LocalDate chosenDate);

    MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId);

    MealToDisplay getTotals(Integer userId, LocalDate date);
}
