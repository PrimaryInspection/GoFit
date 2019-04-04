package com.company.dao.interfaces;

import com.company.model.MealItem;

import java.time.LocalDate;
import java.util.List;

public interface MealItemDao extends CrudDao <MealItem> {
    MealItem getItem(String name);

}
