package com.company.service;

import com.company.model.MealItem;

import java.util.List;

public interface IMealItemService {

    boolean addMealItem(MealItem mealItem);

    List<MealItem> getAll();

    boolean checkProductExist(String name);
}
