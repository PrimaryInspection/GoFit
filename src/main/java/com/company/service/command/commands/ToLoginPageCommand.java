package com.company.service.command.commands;



import com.company.service.command.ActionCommand;
import com.company.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ToLoginPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");

        return page;
    }
}
