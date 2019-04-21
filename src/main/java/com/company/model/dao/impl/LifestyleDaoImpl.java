package com.company.model.dao.impl;

import com.company.model.dao.connection.ConnectionPool;
import com.company.model.dao.mapper.LifestyleDao;
import com.company.model.entity.Lifestyle;
import com.company.model.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LifestyleDaoImpl extends CrudDaoImpl<Lifestyle> implements LifestyleDao {
    private static final Logger logger = LogManager.getLogger(LifestyleDaoImpl.class);
    private static final String SELECT_ALL = QueryManager.getProperty("lifestyleSelectAll");
    private static final String SELECT = QueryManager.getProperty("lifestyleSelect");

    @Override
    public Lifestyle getItem(int id) {
        Lifestyle lifestyle = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                if (resultSet.next()) {
                    lifestyle = new Lifestyle(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                } else {
                    logger.info("No lifestyle with id=" + id + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in 'get lifestyle' method", e.fillInStackTrace());
        }
        return lifestyle;
    }

    @Override
    public List<Lifestyle> getAll() {
        List<Lifestyle> lifestyles = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                lifestyles.add(new Lifestyle(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all lifestyles' method", e.fillInStackTrace());
        }
        return lifestyles;
    }

}
