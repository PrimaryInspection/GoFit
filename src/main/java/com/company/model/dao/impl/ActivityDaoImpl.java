package com.company.model.dao.impl;

import com.company.model.dao.connection.ConnectionPool;
import com.company.model.dao.mapper.ActivityDao;
import com.company.model.entity.Activity;
import com.company.model.entity.ActivityToDisplay;
import com.company.model.utils.QueryManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl extends CrudDaoImpl<Activity> implements ActivityDao {
    private static final Logger logger = LogManager.getLogger(ActivityDaoImpl.class);

    private final String INSERT_ACTIVITY = QueryManager.getProperty("activityInsert");
    private final String SELECT_ALL_ACTIVITIES_BY_ID = QueryManager.getProperty("activitySelectAllById");
    private final String SELECT_TOTAL_ACTIVITIES = QueryManager.getProperty("activitySelectTotal");
    private final String DELETE_ACTIVITY = QueryManager.getProperty("activityDelete");

    @Override
    public boolean addItem(Activity activity) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACTIVITY)) {
            statement.setInt(1, activity.getTimeSpent());
            statement.setDate(2, Date.valueOf(activity.getDate()));
            statement.setInt(3, activity.getUserId());
            statement.setInt(4, activity.getActivityItemId());
            logger.info("Executing query: " + statement.toString());
            resultInsert = statement.executeUpdate();
            logger.info("Result of adding Activity = " + resultInsert);
        } catch (SQLException e) {
            logger.error("Error in adding new 'Activity' , cause: " + e.fillInStackTrace());
        }
        return resultInsert > 0;
    }

    /**
     * IF EXCEPTION - DELETE ActivityToDisplay and try to do smth with Activity
     */
    @Override
    public List<ActivityToDisplay> getAll(Integer userId, LocalDate date) {
        List<ActivityToDisplay> listOfActivities = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTIVITIES_BY_ID)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));

            logger.info("Executing statement: " + statement.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listOfActivities.add(new ActivityToDisplay(
                            resultSet.getInt("id"),
                            resultSet.getString("activityName"),
                            resultSet.getInt("time_spent"),
                            resultSet.getInt("calories")
                    ));
                }
            }
            logger.debug("@@@@@@@@@@@@@@@@@@" + listOfActivities);

            for (ActivityToDisplay a : listOfActivities
                    ) {
                logger.debug("@@@@@@@@@@@@@@@@@@" + a);
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'list of activities' DB, cause: ", e.fillInStackTrace());
        }
        return listOfActivities;
    }

    @Override
    public ActivityToDisplay getTotals(Integer userId, LocalDate chosenDate) {
        ActivityToDisplay totals = new ActivityToDisplay();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOTAL_ACTIVITIES)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(chosenDate));
            logger.info("Executing statement: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totals = new ActivityToDisplay(
                            resultSet.getInt("time_spent"),
                            resultSet.getInt("calories")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting totals activities from database, cause: " + e.fillInStackTrace());
        }
        return totals;
    }

    @Override
    public boolean deleteItem(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACTIVITY)) {
            statement.setInt(1, id);
            logger.info("Executing statement: " + statement.toString());
            resultDelete = statement.executeUpdate();
            logger.info("Result set of deleting = " + resultDelete);
        } catch (SQLException e) {
            logger.error("Error in deleting activity from DB, cause: " + e.fillInStackTrace());
        }
        return resultDelete < 0;
    }
}
