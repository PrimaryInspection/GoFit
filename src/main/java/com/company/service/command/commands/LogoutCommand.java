package com.company.service.command.commands;

import com.company.service.command.ActionCommand;
import com.company.utils.ConfigurationManager;
import com.company.utils.UtilManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.getServletContext().removeAttribute("user");
        request.getServletContext().removeAttribute("login");
        request.getServletContext().removeAttribute("password");
        session.invalidate();
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
