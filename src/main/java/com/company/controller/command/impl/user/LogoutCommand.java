package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {

    /**
     * Logout user backward to login page and delete user's attributes from session
     *
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(String.valueOf(session.getAttribute("user")));
        session.removeAttribute("user");
        session.invalidate();
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
