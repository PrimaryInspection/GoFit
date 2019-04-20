package com.company.exceptions;

import com.company.utils.ConfigurationManager;

import javax.servlet.RequestDispatcher;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String message) {
        super(message);
        String page = ConfigurationManager.getProperty("path.page.error");

    }
}


