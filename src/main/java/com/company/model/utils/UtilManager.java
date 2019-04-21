package com.company.model.utils;

import java.util.ResourceBundle;

public class UtilManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("utils");

    private UtilManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
