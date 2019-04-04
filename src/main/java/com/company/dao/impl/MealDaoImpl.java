package com.company.dao.impl;

import com.company.dao.connection.ConnectionPool;
import com.company.dao.interfaces.MealDao;
import com.company.model.Meal;
import com.company.model.MealToDisplay;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealDaoImpl extends CrudDaoImpl<Meal> implements MealDao {
    private static final Logger logger = LogManager.getLogger(MealDaoImpl.class);

    private static final String SELECT_MEAL_BY_USER_ID = QueryManager.getProperty("mealSelectByUserId");
    private static final String SELECT_MEAL_DAY_TOTALS = QueryManager.getProperty("mealSelectDayTotals");
    private static final String SELECT_TOTALS_MEAL_BY_TYPE = QueryManager.getProperty("mealSelectTotalsByType");
    private static final String INSERT_MEAL = QueryManager.getProperty("mealInsert");
    private static final String DELETE_MEAL = QueryManager.getProperty("mealDelete");

    @Override
    public List<MealToDisplay> getMenu(int userId, LocalDate chosenDate) {
        List<MealToDisplay> menu = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MEAL_BY_USER_ID)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(chosenDate));
            logger.info("Executing statement: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menu.add(new MealToDisplay(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("prod"),
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    ));
                }
                logger.info("Menu: " + menu.toString() + "was got");
            }

        } catch (SQLException e) {
            logger.error("Error in getting menu from data base" + e.getCause());
        }
        return menu;

    }

    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        MealToDisplay totalsMeal = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOTALS_MEAL_BY_TYPE)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));
            statement.setInt(3, mealTypeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalsMeal = new MealToDisplay(
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    );

                }
                logger.info("Total Meals by type :" + totalsMeal + "was got");
            }
        } catch (SQLException e) {
            logger.error("Error in getting total value by  meal type  from DB", e.getCause());
        }
        return totalsMeal;
    }

    @Override
    public MealToDisplay getTotals(Integer userId, LocalDate date) {
        MealToDisplay totalsMealDay = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MEAL_DAY_TOTALS)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalsMealDay = new MealToDisplay(
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    );
                }
                logger.info("Get total day meal :" + resultSet.toString());
            }
        } catch (SQLException e) {
            logger.info("Error in getting totals day meal from database", e.getCause());
        }
        return totalsMealDay;
    }

    @Override
    public boolean addItem(Meal newMeal) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MEAL)) {
            statement.setInt(1, newMeal.getUserId());
            statement.setInt(2, newMeal.getMealItemId());
            statement.setInt(3, newMeal.getWeight());
            statement.setInt(4, newMeal.getMealTypeId());
            statement.setDate(5, Date.valueOf(newMeal.getDate()));
            logger.info("Executing statement :" + statement.toString());
            resultInsert = statement.executeUpdate();
            logger.info("Result set of adding :" + resultInsert);
        } catch (SQLException e) {
            logger.error("Error in adding new meal to DB", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MEAL)) {
            statement.setInt(1, id);
            logger.info("Executing request: " + statement.toString());
            resultDelete = statement.executeUpdate();
            logger.info("Result set of adding = " + resultDelete);
        } catch (SQLException e) {
            logger.error("Error in deleting 'MealDiary entry' from DB", e.getCause());
        }
        return resultDelete > 0;
    }
}
