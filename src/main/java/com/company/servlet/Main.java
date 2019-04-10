package com.company.servlet;

import com.company.dao.connection.ConnectionPool;
import com.company.model.User;

import java.sql.*;

public class Main {

    public User connect() throws SQLException, ClassNotFoundException {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE id = 2";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
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
        }catch(SQLException e){}

        return user;
}


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Main main = new Main();
        main.connect();
        System.out.println(main.connect().toString());

    }

}
