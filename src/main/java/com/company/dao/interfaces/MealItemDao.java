package com.company.dao.interfaces;

import com.company.model.MealItem;

import java.time.LocalDate;
import java.util.List;

public interface MealItemDao extends CrudDao <MealItem> {
    List<MealItem> getMenu(int userId, LocalDate chosenDate);

    MealItem getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId);

    MealItem getTotals(Integer userId, LocalDate date);
}
