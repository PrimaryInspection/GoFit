package com.company.exceptions;

import com.company.utils.ConfigurationManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionErrorException extends RuntimeException {
    public PermissionErrorException(String message , HttpServletResponse response) throws IOException {
        super(message);
        response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
    }
}


