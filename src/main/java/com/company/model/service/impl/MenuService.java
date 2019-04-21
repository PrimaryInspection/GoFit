package com.company.model.service.impl;

import com.company.model.dao.mapper.DaoFactory;
import com.company.model.dao.mapper.MealDao;
import com.company.model.entity.Meal;
import com.company.model.entity.MealToDisplay;
import com.company.model.service.IMenuService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class MenuService implements IMenuService {
    private final static Logger logger = LogManager.getLogger(MenuService.class);
    private static MealDao mealDao = DaoFactory.getMealMethods();
    private static MenuService instance = new MenuService();

    private MenuService() {
    }

    /**
     * Getting singletone instance of MenuService
     *
     * @return MenuService
     */
    public static MenuService getInstance() {
        return instance;
    }

    /**
     * Adding new meal  to database
     *
     * @return boolean(true if addition is done)
     */
    @Override
    public boolean addMeal(Meal meal) {
        logger.info("Adding meal : " + meal.toString());
        return mealDao.addItem(meal);
    }

    /**
     * Getting all user's eaten food from database
     *
     * @return List<MealToDisplay>
     */
    @Override
    public List<MealToDisplay> getUserMenu(int id, LocalDate date) {
        return mealDao.getMenu(id, date);
    }

    /**
     * Getting total count of weight in eaten food
     *
     * @return int totalWeight
     */
    @Override
    public int getTotalWeight(List<MealToDisplay> menu) {
        int totalWeight = 0;
        for (MealToDisplay m : menu) {
            totalWeight += m.getWeight();
        }
        return totalWeight;
    }

    /**
     * Getting total count of calories in eaten food
     *
     * @return int totalCalories
     */
    @Override
    public int getTotalCalories(List<MealToDisplay> menu) {
        int totalCalories = 0;
        for (MealToDisplay m : menu) {
            totalCalories += m.getCalories();
        }
        return totalCalories;
    }

    /**
     * Getting total count of proteins in eaten food
     *
     * @return int totalProteins
     */
    @Override
    public int getTotalProteins(List<MealToDisplay> menu) {
        int totalProteins = 0;
        for (MealToDisplay m : menu) {
            totalProteins += m.getProteins();
        }
        return totalProteins;
    }

    /**
     * Getting total count of fats in eaten food
     *
     * @return int totalFats
     */
    @Override
    public int getTotalFat(List<MealToDisplay> menu) {
        int totalFats = 0;
        for (MealToDisplay m : menu) {
            totalFats += m.getFats();
        }
        return totalFats;
    }

    /**
     * Getting total count of carbs in eaten food
     *
     * @return int totalCarbs
     */
    @Override
    public int getTotalCarbs(List<MealToDisplay> menu) {
        int totalCarbs = 0;
        for (MealToDisplay m : menu) {
            totalCarbs += m.getCarbs();
        }
        return totalCarbs;
    }

    /**
     * Getting  eaten food sorted by meal type(breakfast,dinner..etc)
     *
     * @return MealToDisplay
     */
    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        logger.info("Getting total by MEAL type id: " + mealTypeId);
        return mealDao.getTotalsByMealType(userId, date, mealTypeId);
    }

    /**
     * Getting eaten food during all day
     *
     * @return MealToDisplay
     */
    @Override
    public MealToDisplay getUserMealTotal(Integer userId, LocalDate date) {
        logger.info("Getting user meal total by user ID : " + userId);
        return mealDao.getTotals(userId, date);
    }

    /**
     * Deleting eaten food from page
     *
     * @return true if deleting is done
     */
    @Override
    public boolean deleteMealFromPage(int id) {
        logger.info("Deleting meal by ID: " + id + " from page");
        return mealDao.deleteItem(id);
    }
}
