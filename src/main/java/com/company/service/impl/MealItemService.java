package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.MealItemDao;
import com.company.model.MealItem;
import com.company.service.IMealItemService;
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
        return mealItemDao.addItem(mealItem);
    }

    @Override
    public boolean checkIsMealItemExist(String name) {
    logger.info("Checking is meal item by name" + name + " exist ");
    return mealItemDao.getItem(name) !=null;
    }
}
