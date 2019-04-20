package com.company.model.service;

import com.company.model.entity.MealItem;

import java.util.List;

public interface IMealItemService {
    List<MealItem> getAll();

    boolean addMealItem(MealItem mealItem);

    boolean checkIsMealItemExist(String name);

}
