package com.company.dao.impl;

import com.company.dao.interfaces.MealItemDao;
import com.company.model.MealItem;

import java.time.LocalDate;
import java.util.List;

public class MealItemDaoImpl extends CrudDaoImpl<MealItem> implements MealItemDao {
    @Override
    public List<MealItem> getMenu(int userId, LocalDate chosenDate) {

    }

    @Override
    public MealItem getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
    }

    @Override
    public MealItem getTotals(Integer userId, LocalDate date) {
    }
}
