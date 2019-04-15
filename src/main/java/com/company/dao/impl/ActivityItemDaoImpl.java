package com.company.dao.impl;

import com.company.dao.connection.ConnectionPool;
import com.company.dao.interfaces.ActivityItemDao;
import com.company.model.ActivityItem;
import com.company.utils.QueryManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityItemDaoImpl extends CrudDaoImpl<ActivityItem> implements ActivityItemDao {
    private static final Logger logger = LogManager.getLogger(ActivityDaoImpl.class);

    private String SELECT_ALL_ACTIVITIES = QueryManager.getProperty("activitySelectAll");
    private String SELECT_ACTIVITY_ITEM_BY_NAME = QueryManager.getProperty("activitySelectByName");
    private String INSERT_ACTIVITY_ITEM = QueryManager.getProperty("activityInsertItem");

    @Override
    public List<ActivityItem> getAll() {
        List<ActivityItem> activities = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTIVITIES);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Executing query: " + statement.toString());
            while (resultSet.next()) {
                activities.add(new ActivityItem(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("calories")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Activities' from DB, cause: ", e.fillInStackTrace());
        }
        return activities;
    }

    @Override
    public ActivityItem getActivityItemByName(String name) {
        ActivityItem activityItem = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVITY_ITEM_BY_NAME)) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                if (resultSet.next()) {
                    activityItem = new ActivityItem(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("calories")
                    );
                } else {
                    logger.info("No activityItem with name=" + name + "founded");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'activity item' with name=" + name, e.fillInStackTrace());
        }
        return activityItem;
    }


    @Override
    public boolean addItem(ActivityItem activityItem) {
        int resultAdd = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACTIVITY_ITEM)) {
            statement.setString(1, activityItem.getName());
            statement.setInt(2, activityItem.getCalories());


            logger.info("Executing query is: " + statement.toString());
            resultAdd = statement.executeUpdate();
            if (resultAdd < 1) {
                logger.info("'Activity item was not added");
            }
        } catch (SQLException e) {
            logger.error("Error in adding 'activity item' to DB, cause: ", e.fillInStackTrace());
        }
        return resultAdd > 0;
    }
}
