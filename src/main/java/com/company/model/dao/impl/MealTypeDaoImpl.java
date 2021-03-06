package com.company.model.dao.impl;

import com.company.model.dao.connection.ConnectionPool;
import com.company.model.dao.mapper.MealTypeDao;
import com.company.model.entity.MealType;
import com.company.model.utils.QueryManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealTypeDaoImpl extends CrudDaoImpl<MealType> implements MealTypeDao {

    private static final Logger logger = LogManager.getLogger(MealTypeDaoImpl.class);
    private String selectAll = QueryManager.getProperty("mealTypesSelectAll");

    @Override
    public List<MealType> getAll() {
        List<MealType> mealTypes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                mealTypes.add(new MealType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all meal types' method", e.fillInStackTrace());
            e.printStackTrace();
        }
        return mealTypes;
    }


}