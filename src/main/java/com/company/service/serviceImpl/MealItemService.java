package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.MealItemDao;
import com.company.model.MealItem;
import com.company.service.IMealItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MealItemService implements IMealItemService {
    private static final Logger logger = LogManager.getLogger(MealItemService.class);
    private static MealItemDao mealItemDao = DaoFactory.getMealItemMethods();
    private static MealItemService instance = MealItemService.getInstance();

    private MealItemService() {}

    public static MealItemService getInstance(){return instance;}

    @Override
    public boolean addMealItem(MealItem mealItem) {
        return mealItemDao.addItem(mealItem);
    }
    @Override
    public List<MealItem> getAll() {
        return mealItemDao.getAll();
    }

    @Override
    public boolean checkProductExist(String name) {
        logger.info("Check is meal item with such name already exist");
        return mealItemDao.getItem(name) !=null;
    }
}
