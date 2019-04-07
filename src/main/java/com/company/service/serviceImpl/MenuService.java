package com.company.service.serviceImpl;

import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.MealDao;
import com.company.dao.interfaces.MealTypeDao;
import com.company.model.Meal;
import com.company.model.MealToDisplay;
import com.company.service.IMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class MenuService implements IMenuService{
    private static final Logger logger = LogManager.getLogger(MenuService.class);

    private static MenuService instance = new MenuService();
    private static MealDao mealDao = DaoFactory.getMealMethods();

    private MenuService() {}

    public static MenuService getInstance() {
        return instance;
    }


    @Override
    public boolean addMeal(Meal meal) {
        return mealDao.addItem(meal);
    }

    @Override
    public List<MealToDisplay> getUserMenu(int id, LocalDate chosenDate) {
        return mealDao.getMenu(id,chosenDate);
    }

    @Override
    public int getTotalWeight(List<MealToDisplay> menu) {
        int totalWeight=0;
        for (MealToDisplay meal : menu){
            totalWeight +=meal.getWeight();
        }
        return totalWeight;
    }

    @Override
    public String getTotalCalories(List<MealToDisplay> menu) {
    float totalCalories = 0.0f;
    for(MealToDisplay meal : menu){
        totalCalories +=meal.getCalories();
    }
        logger.debug("---->" + new DecimalFormat("####,####").format(totalCalories));
        return new DecimalFormat("####,####").format(totalCalories);
    }

    @Override
    public String getTotalProteins(List<MealToDisplay> menu) {
        float totalProteins = 0.0f;
        for(MealToDisplay meal : menu){
            totalProteins +=meal.getProtein();
        }
        logger.debug("---->" + new DecimalFormat(".##").format(totalProteins));
        return new DecimalFormat(".##").format(totalProteins);
    }

    @Override
    public String getTotalFat(List<MealToDisplay> menu) {
        float totalFats = 0.0f;
        for(MealToDisplay meal : menu){
            totalFats +=meal.getFat();
        }
        logger.debug("---->" + new DecimalFormat(".##").format(totalFats));
        return new DecimalFormat(".##").format(totalFats);
    }

    @Override
    public String getTotalCarbs(List<MealToDisplay> menu) {
        float totalCarbs = 0.0f;
        for(MealToDisplay meal : menu){
            totalCarbs +=meal.getCarbs();
        }
        logger.debug("---->" + new DecimalFormat(".#").format(totalCarbs));
        return new DecimalFormat(".#").format(totalCarbs);
    }

    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        return mealDao.getTotalsByMealType(userId,date,mealTypeId);
    }

    @Override
    public MealToDisplay getUserFoodTotal(Integer userId, LocalDate date) {
        return mealDao.getTotals(userId,date);
    }

    @Override
    public boolean deleteFromFoodDiary(int id) {
        return mealDao.deleteItem(id);
    }
}
