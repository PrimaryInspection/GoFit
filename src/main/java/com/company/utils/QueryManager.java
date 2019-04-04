package com.company.utils;

import java.util.ResourceBundle;

public class QueryManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("mysqlQueries");

    private QueryManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
