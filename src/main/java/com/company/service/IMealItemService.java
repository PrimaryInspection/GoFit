package com.company.service;

import com.company.model.MealItem;

import java.util.List;

public interface IMealItemService {
    List<MealItem> getAll();

    boolean addMealItem(MealItem mealItem);

    boolean checkIsMealItemExist(String name);

}
