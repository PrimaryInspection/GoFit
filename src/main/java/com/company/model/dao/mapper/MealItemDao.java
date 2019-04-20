package com.company.model.dao.mapper;

import com.company.model.entity.MealItem;

public interface MealItemDao extends CrudDao <MealItem> {
    MealItem getItem(String name);

}
