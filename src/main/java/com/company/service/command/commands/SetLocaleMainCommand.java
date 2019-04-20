package com.company.service.command.commands;

import com.company.service.command.ActionCommand;
import com.company.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleMainCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SetLocaleCommand.class);

    @SuppressWarnings("all")
    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        String page = (ConfigurationManager.getProperty("path.page.main"));
        HttpSession session = request.getSession();

        switch (request.getParameter("locale")) {
            case "EN":
                session.setAttribute("locale", "en_US");
                session.setAttribute("selectedLocale", "EN");
                break;
            case "UA":
            default:
                session.setAttribute("locale", "uk_UA");
                session.setAttribute("selectedLocale", "UA");
                break;
        }
        logger.info("Locale set to:" + session.getAttribute("locale"));
        return page;
    }
}
