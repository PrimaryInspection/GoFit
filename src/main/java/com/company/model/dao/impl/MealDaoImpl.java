package com.company.model.dao.impl;

import com.company.model.dao.connection.ConnectionPool;
import com.company.model.dao.mapper.MealDao;
import com.company.model.exceptions.DataBaseException;
import com.company.model.entity.Meal;
import com.company.model.entity.MealToDisplay;
import com.company.model.utils.QueryManager;
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
            logger.info("Executing query: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menu.add(new MealToDisplay(
                            resultSet.getInt("id"),
                            resultSet.getString("meal"),
                            resultSet.getString("prod"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getInt("proteins"),
                            resultSet.getInt("fats"),
                            resultSet.getInt("carbs")
                    ));
                }
                logger.info("Executed Menu: " + menu.toString() + "was got");
            }

        } catch (SQLException e) {
            logger.error("Error in getting 'menu' from DB, cause: " + e.fillInStackTrace());
            try {
                throw new DataBaseException("Error during getting food menu from data base");
            } catch (DataBaseException e1) {
                e1.printStackTrace();
            }
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
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    totalsMeal = new MealToDisplay(
                            resultSet.getInt("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getInt("proteins"),
                            resultSet.getInt("fats"),
                            resultSet.getInt("carbs")
                    );

                }
                logger.info("Total Meals by type :" + totalsMeal + "was got");
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'total value by  meal type'  from DB, cause: ", e.fillInStackTrace());
            e.printStackTrace();
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
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    totalsMealDay = new MealToDisplay(
                            resultSet.getInt("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getInt("proteins"),
                            resultSet.getInt("fats"),
                            resultSet.getInt("carbs")
                    );
                }
                logger.info("Get total day meal :" + resultSet.toString() + "was got");
            }
        } catch (SQLException e) {
            logger.info("Error in getting 'totals day meal' from DB, cause: ", e.fillInStackTrace());
            e.printStackTrace();
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
            logger.info("Executing query :" + statement.toString());
            resultInsert = statement.executeUpdate();
            logger.info("Result set of adding :" + resultInsert);
        } catch (SQLException e) {
            logger.error("Error in adding new meal to DB, cause: ", e.fillInStackTrace());
            e.printStackTrace();
        }
        return resultInsert > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MEAL)) {
            statement.setInt(1, id);
            logger.info("Executing query: " + statement.toString());
            resultDelete = statement.executeUpdate();
            logger.info("Result set of adding = " + resultDelete);
        } catch (SQLException e) {
            logger.error("Error in deleting 'Meal by id' from DB, cause", e.fillInStackTrace());
            e.printStackTrace();
        }
        return resultDelete < 0;
    }
}
