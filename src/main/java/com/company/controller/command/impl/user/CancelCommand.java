package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CancelCommand.class);

    /**
     * Canceling some action, and backward to main page
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String page = ConfigurationManager.getProperty("path.page.main");

        return page;
    }
}
