package com.company.model.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class RegexManager {
    private static final Logger LOGGER = LogManager.getLogger(RegexManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("regex");

    private RegexManager() {

    }

    public static String getProperty(String key) {

        return resourceBundle.getString(key);
    }
}
