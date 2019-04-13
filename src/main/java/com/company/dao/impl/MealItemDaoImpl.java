package com.company.dao.impl;

import com.company.dao.connection.ConnectionPool;
import com.company.dao.interfaces.MealItemDao;
import com.company.model.MealItem;
import com.company.utils.QueryManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MealItemDaoImpl extends CrudDaoImpl<MealItem> implements MealItemDao {
    private static final Logger logger = LogManager.getLogger(MealItemDaoImpl.class);
    private String SELECT_ALL_MEAL_ITEM = QueryManager.getProperty("mealItemSelectAll");
    private String SELECT_MEAL_ITEM_BY_ID = QueryManager.getProperty("mealItemSelectById");
    private String SELECT_MEAL_ITEM_BY_NAME = QueryManager.getProperty("mealItemSelectByName");
    private String INSERT_MEAL_ITEM = QueryManager.getProperty("mealItemInsert");
    private String DELETE_MEAL_ITEM_BY_ID = QueryManager.getProperty("mealItemDeleteById");

    @Override
    public List<MealItem> getAll() {
        List<MealItem> mealItems = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MEAL_ITEM);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Executing query: " + statement.toString());
            while (resultSet.next()) {
                mealItems.add(new MealItem(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("fats"),
                        resultSet.getInt("calories"),
                        resultSet.getFloat("proteins"),
                        resultSet.getFloat("carbs")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Activities' from DB, cause: ", e.getCause());
            e.printStackTrace();
        }
        return mealItems;
    }

    @Override
    public MealItem getItem(int id) {
        MealItem mealItem = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MEAL_ITEM_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    mealItem = new MealItem(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("fats"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("proteins"),
                            resultSet.getFloat("carbs")

                    );
                    logger.info("No meal item with id: " + id + "was founded");
                }
            }
        } catch (SQLException e) {
            logger.info("Error in 'get item by id' :" + id + " cause: ", e.getCause());
            e.printStackTrace();
        }
        return mealItem;
    }

    @Override
    public boolean addItem(MealItem mealItem) {
        int resultAdd = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MEAL_ITEM)) {
            statement.setString(1, mealItem.getName());
            statement.setInt(2, mealItem.getCalories());
            statement.setFloat(3, mealItem.getProteins());
            statement.setFloat(4, mealItem.getFats());
            statement.setFloat(5, mealItem.getCarbs());
            logger.info("Executing query: " + statement.toString());
            resultAdd = statement.executeUpdate();
            if (resultAdd < 1) {
                logger.info("Meal item was not added");
            }
        } catch (SQLException e) {
            logger.info("Error in  'adding meal item', cause: " + e.getCause());
            e.printStackTrace();
        }
        return resultAdd > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MEAL_ITEM_BY_ID)) {
            statement.setInt(1, id);
            logger.info("Executing query :" + statement.toString());
            resultDelete = statement.executeUpdate();
            if (resultDelete < 1) {
                logger.info("Meal item was not deleted.");
            }
        } catch (SQLException e) {
            logger.error("Error in 'deleting meal item with id:" + id + ",cause  " , e.getCause());
            e.printStackTrace();
        }
        return resultDelete > 0;
    }

    @Override
    public MealItem getItem(String name) {
        MealItem mealItem = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MEAL_ITEM_BY_NAME)) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    mealItem = new MealItem(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("fats"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("proteins"),
                            resultSet.getFloat("carbs")

                    );
                    logger.info("No meal item with name: " + name + "was founded");
                }
            }
        } catch (SQLException e) {
            logger.info("Error in 'get item by name': " + name + ",cause: ", e.getCause());
            e.printStackTrace();
        }
        return mealItem;
    }
}
