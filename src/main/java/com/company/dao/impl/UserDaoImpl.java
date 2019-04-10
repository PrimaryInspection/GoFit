package com.company.dao.impl;

import com.company.dao.connection.ConnectionPool;
import com.company.dao.factory.DaoFactory;
import com.company.dao.interfaces.UserDao;
import com.company.model.User;
import com.company.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends CrudDaoImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SELECT_ALL_USERS = QueryManager.getProperty("userSelectAll");
    private static final String SELECT_ALL_USERS_LIMIT_OFFSET = QueryManager.getProperty("userSelectAllLimitOffset");
    private static final String SELECT_USER_BY_ID = QueryManager.getProperty("userSelectById");
    private static final String SELECT_USER_BY_LOGIN = QueryManager.getProperty("userSelectByLogin");
    private static final String INSERT_USER = QueryManager.getProperty("userInsert");
    private static final String UPDATE_USER = QueryManager.getProperty("userUpdate");
    private static final String SELECT_COUNT_USERS = QueryManager.getProperty("userSelectCount");
    private static final String DELETE_USER = QueryManager.getProperty("userDeleteById");
    private static final String UPDATE_STATUS_ID = QueryManager.getProperty("userUpdateStatusId");


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Executing query: " + statement.toString());
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getFloat("weight"),
                        resultSet.getFloat("weight_goal"),
                        resultSet.getInt("height"),
                        resultSet.getInt("calories_norm"),
                        resultSet.getInt("lifestyle_id"),
                        resultSet.getInt("status"),
                        resultSet.getInt("role_id")

                ));

            }
        } catch (SQLException e) {
            logger.error("Error in 'getting all users' from DB, cause: " + e.getCause());
        }
        return users;
    }

    @Override
    public boolean updateStatus(User user) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_ID)
        ) {
            statement.setInt(1, user.getStatus());
            statement.setInt(2, user.getUserId());


            logger.info("Executing query: " + statement.toString());
            resultUpdate = statement.executeUpdate();
            if (resultUpdate < 1) {
                logger.info("User statusId was not updated.");
            }
        } catch (SQLException e) {
            logger.error("Error in updating user statusId", e.getCause());
        }
        return resultUpdate > 0;
    }

    @Override
    public User getItem(int id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("second_name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getFloat("weight"),
                            resultSet.getFloat("weight_goal"),
                            resultSet.getInt("height"),
                            resultSet.getInt("calories_norm"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("status"),
                            resultSet.getInt("role_id")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in get 'user by id': " + id + ", cause: " + e.getCause());

        }
        return user;
    }

    @Override
    public boolean deleteItem(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            logger.info("Executing query: " + statement.toString());
            resultDelete = statement.executeUpdate();
            if (resultDelete < 1) {
                logger.info("User by id :" + id + " was not deleted");

            }
        } catch (SQLException e) {
            logger.error("Error in deleting user by id=" + id +", cause: ", e.getCause());
        }
        return resultDelete > 0;
    }

    @Override
    public boolean addItem(User newEntity) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)
        ) {
            statement.setString(3, newEntity.getFirstName());
            statement.setString(4, newEntity.getSecondName());
            statement.setString(1, newEntity.getLogin());
            statement.setString(2, newEntity.getPassword());
            statement.setString(5, newEntity.getEmail());
            statement.setDate(6, Date.valueOf(newEntity.getBirthday()));
            statement.setFloat(8, newEntity.getWeight());
            statement.setFloat(9, newEntity.getWeightGoal());
            statement.setInt(10, newEntity.getHeight());
            statement.setInt(12, newEntity.getCalories_norm());
            statement.setInt(11, newEntity.getLifestyle_id());

            logger.info("Executing query: " + statement.toString());

            resultInsert = statement.executeUpdate();
            logger.info((resultInsert < 1) ? "User was not added." : resultInsert + " user was successfully added.");

        } catch (SQLException e) {
            logger.error("Error in adding user to DB, cause: ", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public boolean updateItem(User user) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            {
                statement.setDate(1, Date.valueOf(user.getBirthday()));
                statement.setFloat(2, user.getWeight());
                statement.setFloat(3, user.getWeightGoal());
                statement.setInt(4, user.getHeight());
                statement.setInt(5, user.getCalories_norm());
                statement.setInt(6, user.getLifestyle_id());
                statement.setInt(7, user.getUserId());

                logger.info("Executing query: " + statement.toString());
                resultUpdate = statement.executeUpdate();
                if (resultUpdate < 1) {
                    logger.info("User was not update");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in updating user, cause: " + e.getCause());
        }
        return resultUpdate > 0;
    }

    @Override
    public User get(String login) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Executing query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("second_name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getFloat("weight"),
                            resultSet.getFloat("weight_goal"),
                            resultSet.getInt("height"),
                            resultSet.getInt("calories_norm"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("status"),
                            resultSet.getInt("role_id"));
                } else {
                    logger.info("No user with login=" + login + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting user by login: " + login + ", cause: " + e.getCause());

        }
        return user;
    }



    @Override
    public List<User> getAll(int limit, int offset) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_LIMIT_OFFSET)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Executing query: " + statement.toString());
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("second_name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getFloat("weight"),
                            resultSet.getFloat("goal_weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("calories_norm"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("status"),
                            resultSet.getInt("role_id")

                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting  users limit:" + limit + " offsetting:" + offset + " \ncause: " + e.getCause());
        }
        return users;
    }

    @Override
    public int getUsersCount() {
        int result = 0;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_USERS);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            logger.error("Error in obtaining 'number of users', cause: " + e.getCause());
        }
        logger.info("Number of users is :" + result);
        return result;
    }
}
