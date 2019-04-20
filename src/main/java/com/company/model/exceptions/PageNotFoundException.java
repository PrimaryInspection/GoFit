package com.company.model.exceptions;

import com.company.model.utils.*;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String message) {
        super(message);
        String page = ConfigurationManager.getProperty("path.page.error");

    }
}


