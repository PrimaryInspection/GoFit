package com.company.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static String DATASOURCE_NAME="jdbc/dbpool";
    private static volatile DataSource dataSource;

    static {
        try {
            Context initContex=new InitialContext();
            Context context= (Context) initContex.lookup("java:comp/env/jdbc/dbconnect");
            dataSource= (DataSource) context.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
