package com.company.dao.interfaces;

import com.company.model.Meal;

public interface MealDao extends CrudDao<Meal> {
    Meal get(String name);
}
