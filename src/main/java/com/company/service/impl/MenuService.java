package com.company.service.impl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.MealDao;
import com.company.model.Meal;
import com.company.model.MealToDisplay;
import com.company.service.IMenuService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class MenuService implements IMenuService {
    private final static Logger logger = LogManager.getLogger(MenuService.class);
    private static MealDao mealDao = DaoFactory.getMealMethods();
    private static MenuService instance = new MenuService();

    private MenuService(){}

    public static MenuService getInstance(){return instance;}

    @Override
    public boolean addMeal(Meal meal) {
        logger.info("Adding meal : " + meal.toString());
        return mealDao.addItem(meal);
    }

    @Override
    public List<MealToDisplay> getUserMenu(int id, LocalDate date) {
        return mealDao.getMenu(id,date);
    }

    @Override
    public int getTotalWeight(List<MealToDisplay> menu) {
        int totalWeight=0;
        for (MealToDisplay m: menu) {
            totalWeight += m.getWeight();
        }
        return totalWeight;
    }

    @Override
    public String getTotalCalories(List<MealToDisplay> menu) {
        float totalCalories=0.f;
        for (MealToDisplay m: menu) {
            totalCalories += m.getCalories();
        }
        logger.debug("CALORIES------->" + new DecimalFormat("###,###").format(totalCalories));
        return new DecimalFormat("###,###").format(totalCalories);
    }

    @Override
    public String getTotalProteins(List<MealToDisplay> menu) {
        float totalProteins=0.f;
        for (MealToDisplay m: menu) {
            totalProteins += m.getCalories();
        }
        logger.debug("PROTEINS-------->" + new DecimalFormat("###,###").format(totalProteins));
        return new DecimalFormat("###,###").format(totalProteins);
    }

    @Override
    public String getTotalFat(List<MealToDisplay> menu) {
        float totalFats=0.f;
        for (MealToDisplay m: menu) {
            totalFats += m.getCalories();
        }
        logger.debug("FATS-------->" + new DecimalFormat("###,###").format(totalFats));
        return new DecimalFormat("###,###").format(totalFats);    }

    @Override
    public String getTotalCarbs(List<MealToDisplay> menu) {
        float totalCarbs=0.f;
        for (MealToDisplay m: menu) {
            totalCarbs += m.getCalories();
        }
        logger.debug("CARBS-------->" + new DecimalFormat("###,###").format(totalCarbs));
        return new DecimalFormat("###,###").format(totalCarbs);    }

    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        logger.info("Getting total by MEAL type id: " + mealTypeId);
        return mealDao.getTotalsByMealType(userId,date,mealTypeId);
    }

    @Override
    public MealToDisplay getUserMealTotal(Integer userId, LocalDate date) {
        logger.info("Getting user meal total by user ID : " + userId);
        return mealDao.getTotals(userId,date);
    }

    @Override
    public boolean deleteMealFromPage(int id) {
        logger.info("Deleting meal by ID: " + id + " from page");
        return mealDao.deleteItem(id);
    }
}
