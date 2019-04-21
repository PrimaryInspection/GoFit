package com.company.model.exceptions;

import com.company.model.utils.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionErrorException extends RuntimeException {
    public PermissionErrorException(String message , HttpServletResponse response) throws IOException {
        super(message);
        response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
    }
}


