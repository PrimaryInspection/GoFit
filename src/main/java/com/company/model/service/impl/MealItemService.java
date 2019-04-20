package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.MealItemDao;
import com.company.model.entity.MealItem;
import com.company.model.service.IMealItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MealItemService implements IMealItemService {
    private final static Logger logger = LogManager.getLogger(MealItemService.class);
    private static MealItemDao mealItemDao = DaoFactory.getMealItemMethods();
    private static MealItemService instance = new MealItemService();

    private MealItemService() {}

    public static MealItemService getInstance(){return instance;}

    @Override
    public List<MealItem> getAll() {
        return mealItemDao.getAll();
    }

    @Override
    public boolean addMealItem(MealItem mealItem) {
        logger.info("Getting meal item :" + mealItem.toString());
        return mealItemDao.addItem(mealItem);    }


    @Override
    public boolean checkIsMealItemExist(String name) {
    logger.info("Checking is meal item by name" + name + " exist ");
    return mealItemDao.getItem(name) !=null;
    }


}
