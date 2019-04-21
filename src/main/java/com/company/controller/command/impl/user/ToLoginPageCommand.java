package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ToLoginPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
