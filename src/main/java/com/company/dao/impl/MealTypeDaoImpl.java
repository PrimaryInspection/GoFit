package com.company.dao.impl;

import com.company.dao.connection.ConnectionPool;
import com.company.dao.interfaces.MealTypeDao;
import com.company.model.MealType;
import com.company.utils.QueryManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealTypeDaoImpl extends CrudDaoImpl<MealType> implements MealTypeDao {
    private static final Logger logger= LogManager.getLogger(MealItemDaoImpl.class);

    private String SELECT_ALL_TYPES= QueryManager.getProperty("mealTypesSelectAll");

    @Override
    public List<MealType> getAll() {

        List<MealType> mealTypes = new ArrayList<>();

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TYPES);
            ResultSet resultSet = statement.executeQuery()){
            logger.info("Executing query is: " + statement.toString());
            while (resultSet.next()){
                mealTypes.add(new MealType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (SQLException e) {
            logger.error("Error in getting all meal types, cause: " + e.getCause());
        }
        return mealTypes;
    }
}
